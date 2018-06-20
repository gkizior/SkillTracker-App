package com.wmp.controller;

import com.wmp.helper.EmpIds;
import com.wmp.helper.Skills;
import com.wmp.helper.SkillsOnly;
import com.wmp.helper.StringBody;
import com.wmp.model.Skill;
import com.wmp.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garrett Kizior on 5/30/2018.
 */

@RestController
@RequestMapping("/api")
public class SkillController {

	@Autowired
	SkillRepository skillRepository;

	// Get All Unique Skills
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/skills/unique")
	public List<String> getAllUniqueSkills() {
		List<String> uniqueSkills = new ArrayList<String>();
		List<SkillsOnly> obj = new ArrayList<SkillsOnly>(skillRepository.findDistinctBy());
		for (SkillsOnly o : obj) {
			if (o.getSkill() != null)
				uniqueSkills.add(o.getSkill());
		}
		return uniqueSkills;
	}

	// Create a new Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills")
	public Skill createSkill(@Valid @RequestBody Skill skill) {
		if (skill.getSkill() == null) {
			return skill;
		}
		skill.setCreatedAt();
		return skillRepository.save(skill);
	}

	// Create a new Skill for a list of Employees
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/create")
	public List<Skill> createSkillForEmps(@Valid @RequestBody EmpIds empIds) {
		if (empIds.getSkill().length() < 1) {
			return new ArrayList<Skill>();
		}

		// If skill exists, update instead
		if (this.skillRepository.findBySkill(empIds.getSkill()).size() > 0) {
			return this.updateSkillForEmps(empIds);
		}

		// Create a list of skill(s) to add
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		if (empIds.getEmpIds().size() < 1) {
			Skill s = new Skill();
			s.setId(0);
			s.setSkill(empIds.getSkill());
			s.setCreatedAt();
			skillsToAdd.add(s);
		} else {
			for (Integer empId : empIds.getEmpIds()) {
				Skill s = new Skill();
				s.setId(empId);
				s.setSkill(empIds.getSkill());
				s.setCreatedAt();
				skillsToAdd.add(s);
			}
		}
		return skillRepository.save(skillsToAdd);
	}

	// Updates the Employees that have the skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/skills/update")
	public List<Skill> updateSkillForEmps(@Valid @RequestBody EmpIds empIds) {
		List<Skill> skills = this.skillRepository.findAll();
		List<Integer> empsToAdd = empIds.getEmpIds();
		List<Skill> updateIndexSkills = new ArrayList<Skill>();

		// Removes all Employee Id's that already have the skill. If the Employee has
		// the skill and the EmpId list does not the skill reference is deleted
		for (Skill s : skills) {
			if (s.getSkill().equals(empIds.getSkill())) {
				if (empIds.getEmpIds().contains((int) s.getId())) {
					empsToAdd.remove(Integer.valueOf((int) s.getId()));
				} else {
					updateIndexSkills.add(s);
					this.skillRepository.delete(s);
				}
			}
		}
		
		// Adds the skill to the rest of the Employee Id's
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for (Integer empId : empsToAdd) {
			Skill s = new Skill();
			s.setId(empId);
			s.setSkill(empIds.getSkill());
			s.setCreatedAt();
			updateIndexSkills.add(s);
			skillsToAdd.add(s);
		}
		skillRepository.save(skillsToAdd);
		return updateIndexSkills;
	}

	// Creates new Skills for a single Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/{id}")
	public long createSkills(@PathVariable(value = "id") long empId, @Valid @RequestBody Skills skills) {
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for (String skill : skills.getSkills()) {
			if (skillRepository.findByIdAndSkill(empId, skill) == null) {
				Skill temp = new Skill();
				temp.setId(empId);
				temp.setSkill(skill);
				skillsToAdd.add(temp);
			} else {
				return -1;
			}
		}
		for (Skill skill : skillsToAdd) {
			skillRepository.save(skill);
		}
		return empId;
	}

	// Delete a Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/skills/{id}")
	public long deleteSkill(@PathVariable(value = "id") long id) {
		List<Skill> skills = skillRepository.findById(id);
		skillRepository.delete(skills);
		return id;
	}

	// Removes all skill references for all Employees with the skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/skills/removeAll")
	public List<Skill> removeSkillBySkill(@Valid @RequestBody StringBody skill) {
		List<Skill> skills = skillRepository.findBySkill(skill.getName());
		skillRepository.delete(skills);
		return skills;
	}
}
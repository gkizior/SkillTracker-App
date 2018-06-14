package com.wmp.controller;

import com.wmp.helper.EmpIds;
import com.wmp.helper.SkillsOnly;
import com.wmp.helper.StringBody;
import com.wmp.model.Skill;
import com.wmp.model.Skills;
import com.wmp.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	// Get All Skills
	// @GetMapping("/skills")
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/skills")
	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}

	// @GetMapping("/skills")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/skills/unique")
	public List<String> getAllUniqueSkills() {
		List<String> uniqueSkills = new ArrayList<String>();
		List<SkillsOnly> obj = new ArrayList<SkillsOnly>(skillRepository.findDistinctBy());
		
		for(SkillsOnly o : obj) {
			if(o.getSkill() != null) uniqueSkills.add(o.getSkill());
		}
		
		return uniqueSkills;
	}

	// Create a new Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills")
	public Skill createSkill(@Valid @RequestBody Skill skill) {
		if(skill.getSkill() == null) {
			return skill;
		}
		skill.setCreatedAt();
		return skillRepository.save(skill);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/create")
	public List<Skill> createSkillForEmps(@Valid @RequestBody EmpIds empIds) {
		if(empIds.getSkill().length() < 1 || empIds.getEmpIds().size() < 1) {
			return new ArrayList<Skill>();
		}
		if(this.skillRepository.findBySkill(empIds.getSkill()).size() > 0) {
			return this.updateSkillForEmps(empIds);
		}
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for(Integer empId : empIds.getEmpIds()) {
			Skill s = new Skill();
			s.setId(empId);
			s.setSkill(empIds.getSkill());
			s.setCreatedAt();
			skillsToAdd.add(s);
		}
		return skillRepository.save(skillsToAdd);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/skills/update")
	public List<Skill> updateSkillForEmps(@Valid @RequestBody EmpIds empIds) {
		List<Skill> skills = this.skillRepository.findAll();
		List<Integer> empsToAdd = empIds.getEmpIds();
		for(Skill s : skills) {
			if(s.getSkill().equals(empIds.getSkill())) {
				if(empIds.getEmpIds().contains((int)s.getId())) {
					empsToAdd.remove(Integer.valueOf((int)s.getId()));
				} else {
					this.skillRepository.delete(s);
				}
			}
		}
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for(Integer empId : empsToAdd) {
			Skill s = new Skill();
			s.setId(empId);
			s.setSkill(empIds.getSkill());
			s.setCreatedAt();
			skillsToAdd.add(s);
		}
		return skillRepository.save(skillsToAdd);
	}

	// Create a new Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/{id}")
	public ResponseEntity<?> createSkills(@PathVariable(value = "id") long empId, @Valid @RequestBody Skills skills) {
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for (String skill : skills.getSkills()) {
			if (skillRepository.findByIdAndSkill(empId, skill) == null) {
				Skill temp = new Skill();
				temp.setId(empId);
				temp.setSkill(skill);
				skillsToAdd.add(temp);
			} else {
				return ResponseEntity.badRequest().build();
			}
		}
		for (Skill skill : skillsToAdd) {
			skillRepository.save(skill);
		}
		return ResponseEntity.ok().build();
	}

	// Get a Single Skill
	@GetMapping("/skills/{id}")
	public List<Skill> getSkillById(@PathVariable(value = "id") long skillId) {
		return skillRepository.findById(skillId);
	}

	// Delete a Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/skills/{id}")
	public ResponseEntity<?> deleteSkill(@PathVariable(value = "id") long id) {
		List<Skill> skills = skillRepository.findById(id);
		skillRepository.delete(skills);
		return ResponseEntity.ok().build();
	}

	// Delete a Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/skills/remove/{id}")
	public ResponseEntity<?> removeSkills(@PathVariable(value = "id") long empId, @Valid @RequestBody Skills skills) {
		for (String skill : skills.getSkills()) {
			Skill toDelete = skillRepository.findByIdAndSkill(empId, skill);
			skillRepository.delete(toDelete);
		}
		return ResponseEntity.ok().build();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/skills/removeAll")
	public ResponseEntity<?> removeSkillBySkill(@Valid @RequestBody StringBody skill) {
		List<Skill> skills = skillRepository.findBySkill(skill.getName());
		skillRepository.delete(skills);
		return ResponseEntity.ok().build();
	}
	
}
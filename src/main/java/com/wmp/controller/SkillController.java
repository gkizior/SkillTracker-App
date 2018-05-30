package com.wmp.controller;

import com.wmp.exception.ResourceNotFoundException;
import com.wmp.model.Employee;
import com.wmp.model.Skill;
import com.wmp.model.Skills;
import com.wmp.repository.EmployeeRepository;
import com.wmp.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillController {

	@Autowired
	SkillRepository skillRepository;

	// Get All Employees
	// @GetMapping("/skills")
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/skills")
	public List<Skill> getAllSkills() {
		return skillRepository.findAll();
	}

	// Create a new Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills")
	public Skill createSkill(@Valid @RequestBody Skill skill) {
		skill.setCreatedAt();
		return skillRepository.save(skill);
	}

	// Create a new Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/{id}")
	public void createSkills(@PathVariable(value = "id") long skillId, @Valid @RequestBody Skills skills) {
		System.out.println(skills.toString());
		for(String skill : skills.getSkills()) {
			Skill temp = new Skill();
			temp.setId(skillId);
			temp.setSkill(skill);
			skillRepository.save(temp);
		}
	}

	// Get a Single Employee
	@GetMapping("/skills/{id}")
	public Skill getSkillById(@PathVariable(value = "id") long skillId) {
		return skillRepository.findById(skillId);
	}

	// Update a Employee
	@PutMapping("/skills/{id}")
	public Skill updateSkill(@PathVariable(value = "id") Long skillId, @Valid @RequestBody Skill skillDetails) {

		Skill skill = skillRepository.findById(skillId);

		skill.setId(skillDetails.getId());
		skill.setSkill(skillDetails.getSkill());

		Skill updatedSkill = skillRepository.save(skill);
		return updatedSkill;
	}

	// Delete a Employee
	@DeleteMapping("/skills/{id}")
	public ResponseEntity<?> deleteSkill(@PathVariable(value = "id") Long skillId) {
		Skill skill = skillRepository.findById(skillId);

		skillRepository.delete(skill);

		return ResponseEntity.ok().build();
	}
}
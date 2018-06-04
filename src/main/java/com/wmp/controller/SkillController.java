package com.wmp.controller;

import com.wmp.model.Skill;
import com.wmp.model.Skills;
import com.wmp.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
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

	// Create a new Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills")
	public Skill createSkill(@Valid @RequestBody Skill skill) {
		skill.setCreatedAt();
		return skillRepository.save(skill);
	}

	// Create a new Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/skills/{id}")
	public ResponseEntity<?> createSkills(@PathVariable(value = "id") long empId, @Valid @RequestBody Skills skills) {
		List<Skill> skillsToAdd = new ArrayList<Skill>();
		for (String skill : skills.getSkills()) {
			if(skillRepository.findByIdAndSkill(empId, skill) == null) {
				Skill temp = new Skill();
				temp.setId(empId);
				temp.setSkill(skill);
				skillsToAdd.add(temp);
			} else {
				return ResponseEntity.badRequest().build();
			}
		}
		for(Skill skill : skillsToAdd) {
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
	public ResponseEntity<?> removeSkill(@PathVariable(value = "id") long empId, @Valid @RequestBody Skills skills) {
		for (String skill : skills.getSkills()) {
			Skill toDelete = skillRepository.findByIdAndSkill(empId, skill);
			skillRepository.delete(toDelete);
		}
		return ResponseEntity.ok().build();
	}
}
package com.wmp.controller;

import com.wmp.model.Skill;
import com.wmp.model.Skills;
import com.wmp.model.Solr;
import com.wmp.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
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
	public void createSkills(@PathVariable(value = "id") long skillId, @Valid @RequestBody Skills skills) {
		for (String skill : skills.getSkills()) {
			Skill temp = new Skill();
			temp.setId(skillId);
			temp.setSkill(skill);
			skillRepository.save(temp);
		}
	}

	// Get a Single Skill
	@GetMapping("/skills/{id}")
	public List<Skill> getSkillById(@PathVariable(value = "id") long skillId) {
		return skillRepository.findById(skillId);
	}

	// Delete a Skill
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/skills/{skillidentity}")
	public ResponseEntity<?> deleteSkill(@PathVariable(value = "skillidentity") String skillidentity) {
		String[] split = skillidentity.split(",");
		if(split.length == 2) {
			Skill skill = skillRepository.findByIdAndSkill(Long.parseLong(split[0]), split[1]);
			if(skill != null) skillRepository.delete(skill);
		}
		return ResponseEntity.ok().build();
	}
}
package com.wmp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmp.model.Skill;
import com.wmp.repository.SkillRepository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@RestController
public class SkillController {
	
 @Autowired
 private SkillRepository skillRepository;
 
 @CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/")
 public String SpringBootSolrExample() {
      return "Welcome to Spring Boot solr Example";
 }
 
 @CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/delete")
 public String deleteAllSkills() {
     try { //delete all skills from solr core
      skillRepository.deleteAll();
      return "emps deleted succesfully!";
     }catch (Exception e){
       return "Failed to delete emps";
     }
 }
 
 @CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/save")
 public String saveAllSkills() {
 //Store Skills
	 String[] emp1 = {"Java","AWS","DOT NET", "Angular", "Spring", "Solr", "Spring Boot"};
	 String[] emp2 = {"AWS","DOT NET", "Angular", "Spring", "Solr", "Spring Boot"};
	 String[] emp3 = {"Java","DOT NET", "Angular", "Spring", "Solr", "Spring Boot"};
       skillRepository.save(Arrays.asList(
       new Skill("1", "Matthew","Smith", "Consultant", emp1),
       new Skill("2", "Mark", "Johnson", "Consultant", emp2),
       new Skill("3", "Luke", "Williams", "Consultant", emp3)));
       return "3 emps saved!!!";
 }
 
 @CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/getAll")
 public List<Skill> getAllSkills() {
       List<Skill> skills = new ArrayList<>();
       // iterate all skills and add it to list
       for (Skill skill : this.skillRepository.findAll()) {
       skills.add(skill);
 }
 return skills;
 }
}
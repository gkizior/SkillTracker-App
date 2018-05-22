package com.wmp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
 
 @RequestMapping("/")
 public String SpringBootSolrExample() {
      return "Welcome to Spring Boot solr Example";
 }
 
 @RequestMapping("/delete")
 public String deleteAllSkills() {
     try { //delete all skills from solr core
      skillRepository.deleteAll();
      return "skills deleted succesfully!";
     }catch (Exception e){
       return "Failed to delete skills";
     }
 }
 
 @RequestMapping("/save")
 public String saveAllSkills() {
 //Store Skills
       skillRepository.save(Arrays.asList(
       new Skill("1", "Matthew","Smith", "Consultant", "Java"),
       new Skill("1", "Matthew","Smith", "Consultant", "AWS"),
       new Skill("1", "Matthew","Smith", "Consultant", "DOT NET"),
       new Skill("1", "Matthew","Smith", "Consultant", "Angular"),
       new Skill("1", "Matthew","Smith", "Consultant", "Spring"),
       new Skill("1", "Matthew","Smith", "Consultant", "Solr"),
       new Skill("1", "Matthew","Smith", "Consultant", "Spring Boot"),
       new Skill("1", "Matthew","Smith", "Consultant", "Solr"),
       new Skill("2", "Mark","Johnson", "Consultant", "Java"),
       new Skill("2", "Mark","Johnson", "Consultant", "AWS"),
       new Skill("2", "Mark","Johnson", "Consultant", "DOT NET"),
       new Skill("2", "Mark","Johnson", "Consultant", "Angular"),
       new Skill("2", "Mark","Johnson", "Consultant", "Spring"),
       new Skill("2", "Mark","Johnson", "Consultant", "Solr"),
       new Skill("2", "Mark","Johnson", "Consultant", "Spring Boot"),
       new Skill("2", "Mark","Johnson", "Consultant", "Solr"),
       new Skill("3", "Luke","Williams", "Consultant", "Java"),
       new Skill("3", "Luke","Williams", "Consultant", "AWS"),
       new Skill("3", "Luke","Williams", "Consultant", "DOT NET"),
       new Skill("3", "Luke","Williams", "Consultant", "Angular"),
       new Skill("3", "Luke","Williams", "Consultant", "Spring"),
       new Skill("3", "Luke","Williams", "Consultant", "Solr"),
       new Skill("3", "Luke","Williams", "Consultant", "Spring Boot"),
       new Skill("3", "Luke","Williams", "Consultant", "Solr")));
       return "21 skills saved!!!";
 }
 
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
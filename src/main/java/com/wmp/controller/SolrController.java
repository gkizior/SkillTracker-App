package com.wmp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wmp.model.Employee;
import com.wmp.model.Solr;
import com.wmp.repository.SolrRepository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@RestController
public class SolrController {

	@Autowired
	private SolrRepository solrRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/")
	public String SpringBootSolrExample() {
		return "Welcome to Spring Boot solr Example";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/delete")
	public String deleteAllSkills() {
		try { // delete all skills from solr core
			solrRepository.deleteAll();
			return "emps deleted succesfully!";
		} catch (Exception e) {
			return "Failed to delete emps";
		}
	}

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping("/save")
//	public String saveAllSkills() {
//		// Store Skills
//		String[] emp1 = { "Java", "AWS", "DOT NET", "Angular", "Spring", "Solr", "Spring Boot" };
//		String[] emp2 = { "AWS", "DOT NET", "Angular", "Spring", "Solr", "Spring Boot" };
//		String[] emp3 = { "Java", "DOT NET", "Angular", "Spring", "Solr", "Spring Boot" };
//		skillRepository.save(Arrays.asList(new Skill("1", "Matthew", "Smith", "Consultant", emp1),
//				new Skill("2", "Mark", "Johnson", "Consultant", emp2),
//				new Skill("3", "Luke", "Williams", "Consultant", emp3),
//				new Skill("4", "John", "King", "Experienced Consultant", emp2),
//				new Skill("5", "Joseph", "Brown", "Senior Consultant", emp1)));
//		return "5 emps saved!!!";
//	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getAll")
	public List<Solr> getAllSkills() {
		List<Solr> skills = new ArrayList<>();
		// iterate all skills and add it to list
		for (Solr skill : this.solrRepository.findAll()) {
			skills.add(skill);
		}
		return skills;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/get/{query}", method = RequestMethod.GET)
	@ResponseBody
	public List<Solr> getQuerySkills(@PathVariable String query) throws UnsupportedOperationException {

		String[] words = query.split(" ");
		List<Solr> emps = new ArrayList<>();
		List<Solr> removeEmps = new ArrayList<>();

		emps = new ArrayList<>(this.solrRepository.findBySkillsContains(words[0]));
		for (int i = 1; i < words.length; i++) {
			removeEmps = new ArrayList<>(this.solrRepository.findBySkillsContains(words[i]));
			emps.retainAll(removeEmps);
		}
		return emps;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getEmployee/{employeeId}", method = RequestMethod.GET)
	@ResponseBody
	public Solr getEmployee(@PathVariable String employeeId) throws UnsupportedOperationException {

		Solr emps = this.solrRepository.findOne(employeeId);

		return emps;
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/postEmployee", method = RequestMethod.POST)
//	public void postEmployee(@RequestBody Skill json) throws Exception {
//		Random rand = new Random();
//		json.setEmpId("" + rand.nextInt(10000));
//		skillRepository.save(json);
//	}

	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/findEmployee/{query}", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Skill> findEmployee(@PathVariable String query) throws UnsupportedOperationException {
//
//		String[] words = query.split(" ");
//		List<Skill> emps = new ArrayList<>();
//		List<Skill> addEmps = new ArrayList<>();
//
//		emps = new ArrayList<>(this.skillRepository.findByQueryAnnotation(words[0]));
//		for (int i = 1; i < words.length; i++) {
//			addEmps = new ArrayList<>(this.skillRepository.findByQueryAnnotation(words[i]));
//			emps.removeAll(addEmps);
//			emps.addAll(addEmps);
//		}
//		return emps;
//	}

}
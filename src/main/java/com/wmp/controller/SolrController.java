package com.wmp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	// @CrossOrigin(origins = "http://localhost:4200")
	// @RequestMapping("/save")
	// public String saveAllSkills() {
	// // Store Skills
	// String[] emp1 = { "Java", "AWS", "DOT NET", "Angular", "Spring", "Solr",
	// "Spring Boot" };
	// String[] emp2 = { "AWS", "DOT NET", "Angular", "Spring", "Solr", "Spring
	// Boot" };
	// String[] emp3 = { "Java", "DOT NET", "Angular", "Spring", "Solr", "Spring
	// Boot" };
	// skillRepository.save(Arrays.asList(new Skill("1", "Matthew", "Smith",
	// "Consultant", emp1),
	// new Skill("2", "Mark", "Johnson", "Consultant", emp2),
	// new Skill("3", "Luke", "Williams", "Consultant", emp3),
	// new Skill("4", "John", "King", "Experienced Consultant", emp2),
	// new Skill("5", "Joseph", "Brown", "Senior Consultant", emp1)));
	// return "5 emps saved!!!";
	// }

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getAll")
	public List<Solr> getAll() {
		List<Solr> skills = new ArrayList<>();
		// iterate all skills and add it to list
		for (Solr skill : this.solrRepository.findAll()) {
			skills.add(skill);
		}
		return skills;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getAllSkills")
	public List<String> getAllSkills() {
		List<String> skillNames = new ArrayList<String>();

		for (Solr skill : this.solrRepository.findAll()) {
			if(skill.getSkills() != null) {
				for(String skillName : skill.getSkills()) {
					if(!skillNames.contains(skillName)) {
						skillNames.add(skillName);
					}
				}
			}
		}
		return skillNames;
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
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getStats", method = RequestMethod.GET)
	@ResponseBody
	public List<Stat> getStats() {
		List<Solr> solrList = this.getAll();
		List<Stat> stats = new ArrayList<Stat>();
		for(Solr solr : solrList) {
			if(solr.getSkills() != null) {
				for(String skill : solr.getSkills()) {
					boolean found = false;
					for(Stat stat: stats) {
						if(stat.getName().equals(skill)) {
							found = true;
							List<Series> addToSeries = stat.getSeries();
							int index = stats.indexOf(stat);
							boolean foundCareerLevel = false;
							for(Series series : stat.getSeries()) {
								if(series.getName().equals(solr.getCareerLevel())) {
									series.setValue(series.getValue() + 1);
									foundCareerLevel = true;
								}
							}
							if(!foundCareerLevel) {
								addToSeries.add(new Series(solr.getCareerLevel(), 1));
							}
							stat.setSeries(addToSeries);
							stats.set(index, stat);
						}
					}
					if(!found) {
						Stat addStat = new Stat();
						List<Series> addSeriesList = new ArrayList<Series>();
						Series addSeries = new Series(solr.getCareerLevel(), 1);
						addSeriesList.add(addSeries);
						addStat.setName(skill);
						addStat.setSeries(addSeriesList);
						stats.add(addStat);
					}
				}
			}
		}
		return stats;
	}

	// @CrossOrigin(origins = "http://localhost:4200")
	// @RequestMapping(value = "/postEmployee", method = RequestMethod.POST)
	// public void postEmployee(@RequestBody Skill json) throws Exception {
	// Random rand = new Random();
	// json.setEmpId("" + rand.nextInt(10000));
	// skillRepository.save(json);
	// }

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findEmployee/{query}", method = RequestMethod.GET)
	@ResponseBody
	public List<Solr> findEmployee(@PathVariable String query) throws UnsupportedOperationException {

		String[] words = query.split(" ");
		List<Solr> emps = new ArrayList<>();
		List<Solr> addEmps = new ArrayList<>();

		emps = new ArrayList<>(this.solrRepository.findByQueryAnnotation(words[0]));
		for (int i = 1; i < words.length; i++) {
			addEmps = new ArrayList<>(this.solrRepository.findByQueryAnnotation(words[i]));
			emps.removeAll(addEmps);
			emps.addAll(addEmps);
		}
		return emps;
	}
	
	@SuppressWarnings("serial")
	class Stat implements Serializable {
		String name;
		List<Series> series;
		
		public Stat(String name, List<Series> series) {
			this.name = name;
			this.series = series;
		}
		
		public Stat() {
			this.name = null;
			this.series = new ArrayList<Series>();
		}

		public String getName() {
			return this.name;
		}
		
		public List<Series> getSeries() {
			return this.series;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setSeries(List<Series> series) {
			this.series = series;
		}
	}
	
	@SuppressWarnings("serial")
	class Series implements Serializable {
		
		String name;
		int value;
		
		public Series(String careerLevel, int i) {
			this.name = careerLevel;
			this.value = i;
		}
		
		public Series() {
			this.name = null;
			this.value = 0;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
	}

}
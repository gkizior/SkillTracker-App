package com.wmp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wmp.helper.CareerLevelAndSkill;
import com.wmp.helper.GraphMatch;
import com.wmp.helper.Match;
import com.wmp.helper.MatchBody;
import com.wmp.helper.Series;
import com.wmp.helper.SeriesList;
import com.wmp.helper.Stat;
import com.wmp.helper.StringBody;
import com.wmp.helper.Strings;
import com.wmp.model.Solr;
import com.wmp.repository.SolrRepository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@RestController
public class SolrController {

	@Autowired
	private SolrRepository solrRepository;

	@Autowired
	private SkillController skillController;

	// Gets all Solr Objects
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getAll")
	public List<Solr> getAll() {
		List<Solr> skills = new ArrayList<>();
		List<String> sortBy = new ArrayList<String>(Arrays.asList("Intern", "Consultant", "Experienced Consultant",
				"Senior Consultant", "Manager", "Principal", "Architect", "Senior Manager", "Senior Principal",
				"Senior Architect", "Director", "Senior Director"));
		for (Solr skill : this.solrRepository.findAll()) {
			skills.add(skill);
		}
		List<ArrayList<Solr>> sorting = new ArrayList<>();
		int cl = 0;
		for (String s : sortBy) {
			sorting.add(new ArrayList<Solr>());
			for (int i = 0; i < skills.size(); i++) {
				if (s.equals(skills.get(i).getCareerLevel())) {
					sorting.get(cl).add(skills.get(i));
				}
			}
			cl++;
		}
		List<Solr> sorted = new ArrayList<Solr>();
		for (ArrayList<Solr> s : sorting) {
			sorted.addAll(s);
		}
		return sorted;
	}

	// Gets Stat data for skills
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/getSkillDatas")
	public List<SeriesList> getSkillDatas(@Valid @RequestBody StringBody skill) throws NullPointerException {
		List<SeriesList> result = new ArrayList<SeriesList>();
		List<String> cls = this.getAllCareerLevels();
		for (String cl : cls) {
			List<Solr> CLAndS = this.getCareerLevelAndSkill(new CareerLevelAndSkill(cl, skill.getName()));
			int numOfCL = this.getNumberOfCareerLevel(new StringBody(cl));
			SeriesList sl = new SeriesList(new ArrayList<Series>(), cl, CLAndS);
			Series noSkill = new Series();
			Series yesSkill = new Series();
			noSkill.setName("Does not know " + skill.getName());
			yesSkill.setName("Knows " + skill.getName());
			noSkill.setValue((numOfCL - CLAndS.size()));
			yesSkill.setValue(CLAndS.size());
			sl.getListSeries().add(noSkill);
			sl.getListSeries().add(yesSkill);
			result.add(sl);
		}
		return result;
	}

	// Gets the Employee solr object with id
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getEmployee/{employeeId}", method = RequestMethod.GET)
	@ResponseBody
	public Solr getEmployee(@PathVariable String employeeId) throws UnsupportedOperationException {
		Solr emps = this.solrRepository.findOne(employeeId);
		return emps;
	}

	// Gets data to construct a skill graph
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getSkillGraph", method = RequestMethod.GET)
	@ResponseBody
	public List<Series> getSkillGraph() {
		List<String> unique = this.skillController.getAllUniqueSkills();
		List<Series> skillGraph = new ArrayList<Series>();
		for (String s : unique) {
			skillGraph.add(new Series(s, 0));
		}
		List<Solr> solrList = this.getAll();
		for (Solr solr : solrList) {
			if (solr.getSkills() != null) {
				int index = 0;
				for (String skill : solr.getSkills()) {
					int value = 0;
					for (Series s : skillGraph) {
						if (s.getName().equals(skill)) {
							value = s.getValue();
							index = skillGraph.indexOf(s);
						}
					}
					Series update = new Series(skill, value + 1);
					skillGraph.set(index, update);
				}
			}
		}
		return skillGraph;
	}

	// Gets all stats for angular graphs
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getStats", method = RequestMethod.GET)
	@ResponseBody
	public List<Stat> getStats() {
		List<Solr> solrList = this.getAll();
		List<Stat> stats = new ArrayList<Stat>();
		for (Solr solr : solrList) {
			if (solr.getSkills() != null) {
				for (String skill : solr.getSkills()) {
					boolean found = false;
					for (Stat stat : stats) {
						if (stat.getName().equals(skill)) {
							found = true;
							List<Series> addToSeries = stat.getSeries();
							int index = stats.indexOf(stat);
							boolean foundCareerLevel = false;
							for (Series series : stat.getSeries()) {
								if (series.getName().equals(solr.getCareerLevel())) {
									series.setValue(series.getValue() + 1);
									foundCareerLevel = true;
								}
							}
							if (!foundCareerLevel) {
								addToSeries.add(new Series(solr.getCareerLevel(), 1));
							}
							stat.setSeries(addToSeries);
							stats.set(index, stat);
						}
					}
					if (!found) {
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

	// Gets match data for angular graphs
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/getMatches")
	public List<GraphMatch> getMatches(@Valid @RequestBody MatchBody matchBody) {
		int teamSize = matchBody.getNumber();
		List<String> skills = matchBody.getSkills();
		if (matchBody.getNumber() == 0) {
			teamSize = (int) this.solrRepository.count();
			skills = this.getAllSkills();
		}
		List<Solr> emps = this.getAll();
		List<Match> result = new ArrayList<Match>();
		for (Solr emp : emps) {
			if (emp.getSkills() != null) {
				int number = 0;
				for (String skill : skills) {
					if (Arrays.asList(emp.getSkills()).contains(skill)) {
						number++;
					}
				}
				result.add(new Match(emp, (double) number));
			}
		}
		if (result != null) {
			Collections.sort(result);
			Collections.reverse(result);
		}
		try {
			if (teamSize == 0)
				return getGraphMatch(result);
			return getGraphMatch(result.subList(0, teamSize));
		} catch (Exception e) {
			return getGraphMatch(result);
		}
	}

	// Get Employees that match query
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/getEmployees")
	public List<Solr> getEmployees(@Valid @RequestBody Strings queries) throws UnsupportedOperationException {
		String[] words = queries.getStrings();
		List<Solr> emps = new ArrayList<>();
		List<Solr> addEmps = new ArrayList<>();
		emps = new ArrayList<>(this.solrRepository.findByQueryAnnotation(words[0]));
		for (int i = 1; i < words.length; i++) {
			addEmps = new ArrayList<>(this.solrRepository.findByQueryAnnotation(words[i]));
			emps.retainAll(addEmps);
		}
		return emps;
	}

	public List<GraphMatch> getGraphMatch(List<Match> matches) {
		List<GraphMatch> gMatch = new ArrayList<>();
		for (Match match : matches) {
			String percent = ("" + (int) (match.getCount()) + " skills");
			GraphMatch gM = new GraphMatch(percent, 1);
			if (gMatch.contains(gM)) {
				gMatch.get(gMatch.indexOf(gM)).setValue(gMatch.get(gMatch.indexOf(gM)).getValue() + 1);
			} else {
				gMatch.add(gM);
			}
		}
		return gMatch;
	}

	// Gets a list of all unique career levels
	public List<String> getAllCareerLevels() {
		List<String> cls = new ArrayList<>();
		// iterate all skills and add it to list
		for (Solr skill : this.solrRepository.findAll()) {
			if (!cls.contains(skill.getCareerLevel())) {
				cls.add(skill.getCareerLevel());
			}
		}
		return cls;
	}

	// Gets the number of employees at a certain career level
	public int getNumberOfCareerLevel(StringBody careerLevel) throws NullPointerException {
		List<Solr> result = this.solrRepository.findByCfnameContains(careerLevel.getName().replaceAll("\\s", ""));
		if (result != null)
			return result.size();
		return 0;
	}

	// Gets a list of Employees with a certain career level and skill
	public List<Solr> getCareerLevelAndSkill(CareerLevelAndSkill CLAndSkill) throws NullPointerException {
		List<Solr> emps = new ArrayList<>();
		for (Solr skill : this.solrRepository.findAll()) {
			if (skill.getSkills() != null) {
				if (skill.getCareerLevel().equals(CLAndSkill.getCareerLevel())
						&& Arrays.asList(skill.getSkills()).contains(CLAndSkill.getSkill())) {
					emps.add(skill);
				}
			}
		}
		return emps;
	}

	// Gets list of all skill names
	public List<String> getAllSkills() {
		List<String> skillNames = new ArrayList<String>();
		for (Solr skill : this.solrRepository.findAll()) {
			if (skill.getSkills() != null) {
				for (String skillName : skill.getSkills()) {
					if (!skillNames.contains(skillName)) {
						skillNames.add(skillName);
					}
				}
			}
		}
		return skillNames;
	}
}
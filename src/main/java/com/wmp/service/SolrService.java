package com.wmp.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wmp.model.Employee;
import com.wmp.model.Skill;
import com.wmp.model.Solr;
import com.wmp.repository.EmployeeRepository;
import com.wmp.repository.SkillRepository;
import com.wmp.repository.SolrRepository;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Garrett Kizior on 6/19/2018.
 */

@Service
public class SolrService {

	@Resource
	private SolrRepository repository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Resource
	SkillRepository skillRepository;

	@Transactional
	public void updateIndex(Long id) {

		String urlString = "http://localhost:8983/solr/skilltracker";
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());

		Employee e = this.employeeRepository.findById(id);

		if (e == null)
			return;

		List<Skill> s = this.skillRepository.findById(id);

		String[] sArray = new String[s.size()];
		int i = 0;
		for (Skill sElement : s) {
			sArray[i++] = sElement.getSkill();
		}

		Solr add = new Solr("" + id, e.getFirstName(), e.getLastName(), e.getCareerLevel(), e.getAddress(), e.getCity(),
				e.getState(), e.getZipcode(), e.getCreatedAt().toString(), e.getUpdatedAt().toString(), sArray,
				e.getCareerLevel(), sArray);

		Solr current = repository.findByIdString("" + id);
		try {
			if (current == null) {
				solr.addBean(add);
				solr.commit();
			} else {
				solr.deleteByQuery("Id:" + id);
				solr.commit();
				solr.addBean(add);
				solr.commit();
			}
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Transactional
	public void deleteIndex(Long id) {
		String urlString = "http://localhost:8983/solr/skilltracker";
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());

		Solr current = repository.findByIdString("" + id);
		try {
			if (current != null) {
				solr.deleteByQuery("Id:" + id);
				solr.commit();
			}
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
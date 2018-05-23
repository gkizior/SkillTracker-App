package com.wmp.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

import com.wmp.model.Skill;

@Repository
public interface SkillRepository extends SolrCrudRepository<Skill, String> {
	
//  List<Skill> findByFirstNameContainsOrLastNameContains(String firstName, String lastName); // find documents whose docTitle ends with specified string
//  List<Skill> findBySkills(String... skill);
	List<Skill> search(String searchTerm);
}
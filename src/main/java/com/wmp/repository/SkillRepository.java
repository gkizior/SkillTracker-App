package com.wmp.repository;

import java.util.List;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

import com.wmp.model.Skill;
public interface SkillRepository extends SolrCrudRepository<Skill, String> {
  List<Skill> findByFirstNameEndsWith(String firstName); // find documents whose docTitle ends with specified string
  List<Skill> findByFirstNameStartsWith(String firstName); // find documents whose docTitle starts with specified string
  List<Skill> findBySkillEndsWith(String skill); //find documents whose docType ends with specified string
  List<Skill> findBySkillStartsWith(String skill);//find documents whose docType start with specified string
}
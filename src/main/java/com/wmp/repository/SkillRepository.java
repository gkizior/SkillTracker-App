package com.wmp.repository;

import java.util.List;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

import com.wmp.model.Skill;
public interface SkillRepository extends SolrCrudRepository<Skill, String> {
}
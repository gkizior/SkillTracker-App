package com.wmp.repository;

import com.wmp.model.Skill;
import com.wmp.helper.SkillsOnly;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Garrett Kizior on 5/30/2018.
 */

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	List<Skill> findById(long id);
	
	Skill findBySkillId(long skillId);
	
	Skill findByIdAndSkill(long id, String skill);
	
	List<Skill> findBySkill(String skill);
	
	List<SkillsOnly> findDistinctBy();
}
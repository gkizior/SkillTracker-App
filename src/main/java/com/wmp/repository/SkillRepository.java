package com.wmp.repository;

import com.wmp.model.Skill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Garrett Kizior on 5/30/2018.
 */

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

	List<Skill> findById(long skillId);
	Skill findByIdAndSkill(long id, String skill);

}
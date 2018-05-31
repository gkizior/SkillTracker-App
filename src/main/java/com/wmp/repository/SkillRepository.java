package com.wmp.repository;

import com.wmp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Garrett Kizior on 5/30/2018.
 */

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

	Skill findById(long skillId);

}
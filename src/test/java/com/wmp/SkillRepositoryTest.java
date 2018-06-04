package com.wmp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wmp.model.Skill;
import com.wmp.repository.SkillRepository;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Garrett Kizior on 5/31/2018.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SkillRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SkillRepository skillRepository;

	@Test
	public void findById_returnsCorrectId() {
		// given
		Skill skill = new Skill();
		skill.setId(1);
		skill.setSkill("Java");
		entityManager.persist(skill);
		entityManager.flush();

		// when
		Skill found = skillRepository.findBySkillId(1);

		// then
		assertThat(found).isEqualTo(skill);
	}

}
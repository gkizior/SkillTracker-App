package com.wmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		List<Skill> testSkillList = null;
		testSkillList.add(new Skill(0, 1, "Java", new Date()));
		entityManager.persist(testSkillList);
		entityManager.flush();

		// when
		List<Skill> found = skillRepository.findById(1);

		// then
		assertThat(found).isEqualTo(testSkillList);
	}

}
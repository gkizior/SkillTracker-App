package com.wmp;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wmp.model.Employee;
import com.wmp.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Garrett Kizior on 5/31/2018.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void findById_returnsCorrectId() {
		// given
		Employee testEmp = new Employee(0, "John", "Doe", new Date(), new Date(), "Consultant", "123 Address St.",
				"City", "State", "12345", new Date(), new Date());
		entityManager.persist(testEmp);
		entityManager.flush();

		// when
		Employee found = employeeRepository.findById(testEmp.getId());

		// then
		assertThat(found.getId()).isEqualTo(testEmp.getId());
	}

}
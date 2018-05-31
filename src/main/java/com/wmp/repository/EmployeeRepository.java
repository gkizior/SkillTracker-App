package com.wmp.repository;

import com.wmp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Garrett Kizior on 5/25/2018.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findById(long employeeId);

}
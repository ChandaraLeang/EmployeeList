package edu.mum.cs545.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	List<Employee> findByLastname(String lastname);
}

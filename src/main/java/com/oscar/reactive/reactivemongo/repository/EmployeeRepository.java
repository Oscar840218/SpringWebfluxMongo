package com.oscar.reactive.reactivemongo.repository;

import com.oscar.reactive.reactivemongo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
}

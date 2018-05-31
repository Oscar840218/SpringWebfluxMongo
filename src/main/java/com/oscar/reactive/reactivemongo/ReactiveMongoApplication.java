package com.oscar.reactive.reactivemongo;

import com.oscar.reactive.reactivemongo.model.Employee;
import com.oscar.reactive.reactivemongo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository) {
		return args -> {
			employeeRepository
					.deleteAll()
					.subscribe(null, null, ()->{
						Stream.of(
								new Employee(UUID.randomUUID().toString(), "Peter", 230000L),
								new Employee(UUID.randomUUID().toString(), "Oscar", 460000L),
								new Employee(UUID.randomUUID().toString(), "Kevin", 580000L),
								new Employee(UUID.randomUUID().toString(), "Cindy", 110000L)
						).forEach(employee -> {
							employeeRepository
									.save(employee)
									.subscribe(System.out::println);
						});
					})
			;
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoApplication.class, args);
	}
}

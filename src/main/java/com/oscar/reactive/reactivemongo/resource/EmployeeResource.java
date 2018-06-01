package com.oscar.reactive.reactivemongo.resource;

import com.oscar.reactive.reactivemongo.model.Employee;
import com.oscar.reactive.reactivemongo.model.EmployeeEvent;
import com.oscar.reactive.reactivemongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployee(@PathVariable("id") final String id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/{id}/events")
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String id) {
        return employeeRepository.findById(id)
                .flatMapMany(employee -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(
                            Stream.generate(EmployeeEvent::new)
                    );
                    return Flux.zip(interval, employeeEventFlux).map(Tuple2::getT2);
                });
    }
}

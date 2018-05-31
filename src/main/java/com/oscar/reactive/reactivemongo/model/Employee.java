package com.oscar.reactive.reactivemongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@AllArgsConstructor
@Document
public class Employee {

    @Id
    private String id;

    private String name;

    private Long salary;

    public Employee(String id, String name, Long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

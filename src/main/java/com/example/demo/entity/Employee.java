package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    private String email;

    private String password;

    private String name;

    @ManyToOne
    @ToString.Exclude
    private Stream stream;

    @ManyToOne
    @ToString.Exclude
    private Employee careerCouch;

    @OneToMany(mappedBy = "careerCouch")
    @ToString.Exclude
    private List<Employee> couchingEmployees;
}

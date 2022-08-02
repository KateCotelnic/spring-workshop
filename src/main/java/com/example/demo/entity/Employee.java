package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Stream stream;

    @ManyToOne
    private Employee careerCouch;

    @OneToMany(mappedBy = "careerCouch")
    private List<Employee> couchingEmployees;
}

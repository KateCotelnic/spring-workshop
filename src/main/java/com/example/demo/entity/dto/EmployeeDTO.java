package com.example.demo.entity.dto;

import com.example.demo.entity.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    private String email;
    private String password;
    private String name;
    private String department;
    private String stream;
    private String careerCouchEmail;

    public static EmployeeDTO employeeToDTO(Employee employee){
        return EmployeeDTO.builder()
                .email(employee.getEmail())
                .password(employee.getPassword())
                .name(employee.getName())
                .stream(employee.getStream().getName())
                .department(employee.getStream().getDepartment())
                .careerCouchEmail(employee.getCareerCouch().getEmail())
                .build();
    }
}

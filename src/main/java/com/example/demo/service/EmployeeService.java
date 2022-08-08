package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Stream;
import com.example.demo.entity.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final StreamRepository streamRepository;

    public List<EmployeeDTO> getAll(){
        return employeeRepository.findAll().stream().map(EmployeeDTO::employeeToDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllBy(String department){
        return employeeRepository.getAllByStream_Department(department).stream().map(EmployeeDTO::employeeToDTO).collect(Collectors.toList());
    }

    public void delete(String email){
        employeeRepository.delete(employeeRepository.getEmployeeByEmail(email));
    }

    public void register(EmployeeDTO employeeDTO){
        Stream stream = streamRepository.getStreamByDepartmentAndName(employeeDTO.getDepartment(), employeeDTO.getStream());
        Employee employee = Employee.builder()
                .email(employeeDTO.getEmail())
                .password(employeeDTO.getPassword())
                .name(employeeDTO.getName())
                .stream(stream)
                .careerCouch(employeeRepository.getEmployeeByEmail(stream.getMentor().getEmail()))
                .build();
        employeeRepository.save(employee);
    }
}

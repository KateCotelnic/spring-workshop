package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Stream;
import com.example.demo.entity.dto.EmployeeDTO;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final StreamRepository streamRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<EmployeeDTO> getAll(){
        return employeeRepository.findAll().stream().map(EmployeeDTO::employeeToDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllBy(String department){
        return employeeRepository.getAllByStream_Department(department).stream().map(EmployeeDTO::employeeToDTO).collect(Collectors.toList());
    }

    public void delete(String email){
        employeeRepository.delete(employeeRepository.getEmployeeByEmail(email).orElseThrow(() -> new NoSuchElementException("User doesn't exist")));
    }

    public void register(EmployeeDTO employeeDTO){
        try {
            employeeRepository.getEmployeeByEmail(employeeDTO.getEmail()).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));
            throw new UserAlreadyExistsException("User with the provided email already exists");
        } catch (NoSuchElementException e){
            Stream stream = streamRepository.getStreamByDepartmentAndName(employeeDTO.getDepartment(), employeeDTO.getStream());
            Employee employee = Employee.builder()
                    .email(employeeDTO.getEmail())
                    .password(passwordEncoder.encode(employeeDTO.getPassword()))
                    .name(employeeDTO.getName())
                    .stream(stream)
                    .careerCouch(employeeRepository.getEmployeeByEmail(stream.getMentor().getEmail()).orElseThrow(() -> new NoSuchElementException("User doesn't exist")))
                    .build();
            employeeRepository.save(employee);
        }
    }
}

package com.example.demo.controller;

import com.example.demo.entity.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getByDepartment(@RequestParam(defaultValue = "") String department){
        if(department.isEmpty())
            return ResponseEntity.ok(employeeService.getAll());
        return ResponseEntity.ok(employeeService.getAllBy(department));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody EmployeeDTO employeeDTO){
        employeeService.register(employeeDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping()
    public void delete(@RequestParam(name = "email") String email){
        employeeService.delete(email);
    }
}

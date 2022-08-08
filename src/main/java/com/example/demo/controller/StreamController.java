package com.example.demo.controller;

import com.example.demo.entity.dto.StreamDTO;
import com.example.demo.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stream")
public class StreamController {

    private final StreamService streamService;

    @GetMapping()
    public ResponseEntity<List<String>> getNamesByDepartment(@RequestParam(name = "department", required = false) String department){
        return ResponseEntity.ok(streamService.getByDepartment(department));
    }

    @PutMapping
    public ResponseEntity<HttpStatus> setNewMentor(@RequestParam(name = "id") String id, @RequestBody String email){
        System.out.println("email = " + email);
        streamService.setNewMentor(Long.parseLong(id), email);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public void createStream(@RequestBody StreamDTO streamDTO){
        streamService.createStream(streamDTO);
    }
}

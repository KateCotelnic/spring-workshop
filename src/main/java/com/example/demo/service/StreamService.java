package com.example.demo.service;

import com.example.demo.entity.Stream;
import com.example.demo.entity.dto.StreamDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StreamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamService{
    private final StreamRepository streamRepository;
    private final EmployeeRepository employeeRepository;

    public List<String> getByDepartment(String department){
        return streamRepository.getAllByDepartment(department).stream().map(Stream::getName).collect(Collectors.toList());
    }

    public void createStream(StreamDTO streamDTO){
        Stream stream = Stream.builder()
                .name(streamDTO.getName())
                .mentor(employeeRepository.getEmployeeByEmail(streamDTO.getMentor()))
                .department(streamDTO.getDepartment())
                .employees(new ArrayList<>())
                .build();
        streamRepository.save(stream);
    }

    public void setNewMentor(Long id, String mentorEmail){
        Stream stream = streamRepository.getStreamById(id);
        System.out.println(employeeRepository.getEmployeeByEmail(mentorEmail));
        stream.setMentor(employeeRepository.getEmployeeByEmail(mentorEmail));
        streamRepository.save(stream);
    }
}

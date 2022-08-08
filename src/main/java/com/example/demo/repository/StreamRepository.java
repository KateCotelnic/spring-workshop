package com.example.demo.repository;

import com.example.demo.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {
    List<Stream> getAllByDepartment(String department);
    Stream getStreamByDepartmentAndName(String department, String name);
    Stream getStreamById(Long id);
}

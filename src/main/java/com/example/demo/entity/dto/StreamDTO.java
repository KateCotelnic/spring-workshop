package com.example.demo.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamDTO {
    private String name;
    private String department;
    private String mentor;
}

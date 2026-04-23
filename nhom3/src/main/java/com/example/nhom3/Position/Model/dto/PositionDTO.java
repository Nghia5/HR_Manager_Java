package com.example.nhom3.Position.Model.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PositionDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String level;
    private UUID departmentId; 
    private Boolean isActive;
}
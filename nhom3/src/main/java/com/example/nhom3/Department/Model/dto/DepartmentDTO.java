package com.example.nhom3.Department.Model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class DepartmentDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private LocalDate establishedDate;
    private Boolean isActive;
}
package com.example.nhom3.Employee.Model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmployeeDTO {
    private UUID id;
    private String code;
    private String fullName;
    private LocalDate dateOfBirth;
    private Byte gender;
    private String email;
    private String phone;
    private String address;
    
    private UUID userId;
    private UUID departmentId;
    private UUID positionId;
    
    private LocalDate hireDate;
    private String contractType;
    private Double salaryCoefficient;
    private String academicDegree;
    private String academicTitle;
    private String specialization;
    
    private Boolean isActive;
}
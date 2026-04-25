package com.example.nhom3.EmployeePosition.Model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeePositionResponse(
        UUID id,
        UUID employeeId,
        String employeeName,
        String employeeCode,
        UUID positionId,
        String positionName,
        String departmentName,
        String description,
        String note,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isActive) {
}
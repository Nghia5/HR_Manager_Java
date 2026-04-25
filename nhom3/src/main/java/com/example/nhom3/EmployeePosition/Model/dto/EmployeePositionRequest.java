package com.example.nhom3.EmployeePosition.Model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record EmployeePositionRequest(
        @NotNull(message = "Nhân viên không được để trống") UUID employeeId,

        @NotNull(message = "Chức vụ không được để trống") UUID positionId,

        String description,
        String note,

        @NotNull(message = "Ngày bắt đầu không được để trống") LocalDateTime startDate,

        LocalDateTime endDate) {
}
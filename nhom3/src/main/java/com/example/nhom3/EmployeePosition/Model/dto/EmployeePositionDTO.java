package com.example.nhom3.EmployeePosition.Model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeePositionDTO {
    private UUID id;
    private UUID employeeId;
    private String employeeName; // Lấy từ bảng Employee
    private UUID positionId;
    private String positionName; // Lấy từ bảng Position
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String note;
    private Boolean isActive;

    // --- GETTERS & SETTERS ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public UUID getPositionId() { return positionId; }
    public void setPositionId(UUID positionId) { this.positionId = positionId; }

    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
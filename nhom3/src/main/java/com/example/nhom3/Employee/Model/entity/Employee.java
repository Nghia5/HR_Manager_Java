package com.example.nhom3.Employee.Model.entity;

import com.example.nhom3.Department.Model.entity.Department;
import com.example.nhom3.Position.Model.entity.Position;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private Byte gender; 

    // Bỏ unique = true vì trong SQL Server, 
    // nhiều giá trị NULL sẽ bị coi là trùng lặp nếu có ràng buộc UNIQUE
    @Column(length = 100) 
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(name = "user_id") // Bỏ unique để tránh lỗi 500 khi lưu nhiều bản ghi NULL
    private UUID userId; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "contract_type", length = 100)
    private String contractType;

    @Column(name = "salary_coefficient")
    private Double salaryCoefficient;

    @Column(name = "academic_degree", length = 100)
    private String academicDegree;

    @Column(name = "academic_title", length = 100)
    private String academicTitle;

    @Column(name = "specialization", length = 100)
    private String specialization;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
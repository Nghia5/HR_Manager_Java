package com.example.nhom3.Department.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom3.Department.Model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
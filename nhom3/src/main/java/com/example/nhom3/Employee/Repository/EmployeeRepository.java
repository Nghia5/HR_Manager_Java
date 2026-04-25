package com.example.nhom3.Employee.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom3.Employee.Model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    // Spring Data JPA sẽ tự lo các thao tác CRUD cơ bản ở đây
}
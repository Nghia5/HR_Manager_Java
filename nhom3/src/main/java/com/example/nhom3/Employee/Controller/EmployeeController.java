package com.example.nhom3.Employee.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nhom3.Employee.Model.entity.Employee;
import com.example.nhom3.Employee.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 1. Lấy danh sách tất cả nhân sự
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    // 2. Lấy thông tin 1 nhân sự theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        Employee emp = employeeService.getEmployeeById(id);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.notFound().build();
    }

    // 3. Thêm mới nhân sự
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createOrUpdateEmployee(employee);
    }

    // 4. Cập nhật thông tin nhân sự
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody Employee employeeDetails) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Cập nhật các trường dữ liệu
        emp.setCode(employeeDetails.getCode());
        emp.setFullName(employeeDetails.getFullName());
        emp.setDateOfBirth(employeeDetails.getDateOfBirth());
        emp.setGender(employeeDetails.getGender());
        emp.setEmail(employeeDetails.getEmail());
        emp.setPhone(employeeDetails.getPhone());
        emp.setAddress(employeeDetails.getAddress());
        emp.setHireDate(employeeDetails.getHireDate());
        emp.setContractType(employeeDetails.getContractType());
        emp.setSalaryCoefficient(employeeDetails.getSalaryCoefficient());
        emp.setAcademicDegree(employeeDetails.getAcademicDegree());
        emp.setAcademicTitle(employeeDetails.getAcademicTitle());
        emp.setSpecialization(employeeDetails.getSpecialization());
        emp.setIsActive(employeeDetails.getIsActive());

        // Cập nhật các liên kết ID (Khóa ngoại)
        if (employeeDetails.getUserId() != null) emp.setUserId(employeeDetails.getUserId());
        if (employeeDetails.getDepartment() != null) emp.setDepartment(employeeDetails.getDepartment());
        if (employeeDetails.getPosition() != null) emp.setPosition(employeeDetails.getPosition());
        
        return ResponseEntity.ok(employeeService.createOrUpdateEmployee(emp));
    }

    // 5. Xóa nhân sự
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
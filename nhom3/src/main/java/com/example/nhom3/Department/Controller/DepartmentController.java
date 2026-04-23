package com.example.nhom3.Department.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nhom3.Department.Model.entity.Department;
import com.example.nhom3.Department.Service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 1. Lấy danh sách tất cả Khoa/Viện
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    // 2. Lấy thông tin 1 Khoa theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable UUID id) {
        Department dept = departmentService.getDepartmentById(id);
        return dept != null ? ResponseEntity.ok(dept) : ResponseEntity.notFound().build();
    }

    // 3. Thêm mới Khoa/Viện
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.createOrUpdateDepartment(department);
    }

    // 4. Cập nhật thông tin Khoa/Viện
    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable UUID id, @RequestBody Department departmentDetails) {
        Department dept = departmentService.getDepartmentById(id);
        if (dept == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Cập nhật các trường dữ liệu
        dept.setCode(departmentDetails.getCode());
        dept.setName(departmentDetails.getName());
        dept.setDescription(departmentDetails.getDescription());
        dept.setEstablishedDate(departmentDetails.getEstablishedDate());
        dept.setIsActive(departmentDetails.getIsActive());
        
        return ResponseEntity.ok(departmentService.createOrUpdateDepartment(dept));
    }

    // 5. Xóa Khoa/Viện
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
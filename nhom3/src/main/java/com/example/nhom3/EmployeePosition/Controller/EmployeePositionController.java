package com.example.nhom3.EmployeePosition.Controller;

import com.example.nhom3.EmployeePosition.Model.dto.EmployeePositionDTO;
import com.example.nhom3.EmployeePosition.Model.entity.EmployeePosition;
import com.example.nhom3.EmployeePosition.Service.EmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee-positions")
@CrossOrigin(origins = "*")
public class EmployeePositionController {

    @Autowired
    private EmployeePositionService service;

    @GetMapping
    public ResponseEntity<List<EmployeePositionDTO>> getAll() {
        try {
            return ResponseEntity.ok(service.getAllPositions());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeePosition> create(@RequestBody EmployeePositionDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createPosition(dto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // THÊM MỚI: Xử lý cập nhật (Sửa)
    @PutMapping("/{id}")
    public ResponseEntity<EmployeePosition> update(@PathVariable UUID id, @RequestBody EmployeePositionDTO dto) {
        try {
            EmployeePosition updated = service.updatePosition(id, dto);
            if (updated != null) return ResponseEntity.ok(updated);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            if (service.softDeletePosition(id)) return ResponseEntity.ok("Đã xóa thành công");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }
}
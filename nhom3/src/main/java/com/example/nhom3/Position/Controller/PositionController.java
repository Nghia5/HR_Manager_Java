package com.example.nhom3.Position.Controller;

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

import com.example.nhom3.Position.Model.entity.Position;
import com.example.nhom3.Position.Service.PositionService;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    // 1. Lấy danh sách tất cả Chức vụ
    @GetMapping
    public List<Position> getAll() {
        return positionService.getAllPositions();
    }

    // 2. Lấy thông tin 1 Chức vụ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable UUID id) {
        Position pos = positionService.getPositionById(id);
        return pos != null ? ResponseEntity.ok(pos) : ResponseEntity.notFound().build();
    }

    // 3. Thêm mới Chức vụ
    @PostMapping
    public Position create(@RequestBody Position position) {
        return positionService.createOrUpdatePosition(position);
    }

    // 4. Cập nhật thông tin Chức vụ
    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable UUID id, @RequestBody Position positionDetails) {
        Position pos = positionService.getPositionById(id);
        if (pos == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật các trường dữ liệu
        pos.setCode(positionDetails.getCode());
        pos.setName(positionDetails.getName());
        pos.setDescription(positionDetails.getDescription());
        pos.setLevel(positionDetails.getLevel());
        pos.setDepartment(positionDetails.getDepartment()); // Cập nhật lại Khoa nếu có đổi
        pos.setIsActive(positionDetails.getIsActive());

        return ResponseEntity.ok(positionService.createOrUpdatePosition(pos));
    }

    // 5. Xóa Chức vụ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.nhom3.EmployeePosition.Service;

import com.example.nhom3.EmployeePosition.Model.dto.EmployeePositionDTO;
import com.example.nhom3.EmployeePosition.Model.entity.EmployeePosition;
import com.example.nhom3.EmployeePosition.Repository.EmployeePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeePositionService {

    @Autowired
    private EmployeePositionRepository repository;

    // Lấy danh sách để đổ ra bảng
    public List<EmployeePositionDTO> getAllPositions() {
        List<Object[]> results = repository.findAllWithNames();
        List<EmployeePositionDTO> dtoList = new ArrayList<>();

        for (Object[] row : results) {
            EmployeePositionDTO dto = new EmployeePositionDTO();
            dto.setId(row[0] != null ? UUID.fromString(row[0].toString()) : null);
            dto.setEmployeeId(row[1] != null ? UUID.fromString(row[1].toString()) : null);
            dto.setPositionId(row[2] != null ? UUID.fromString(row[2].toString()) : null);
            dto.setStartDate(row[3] != null ? (LocalDateTime) row[3] : null);
            dto.setEndDate(row[4] != null ? (LocalDateTime) row[4] : null);
            dto.setDescription(row[5] != null ? row[5].toString() : null);
            dto.setNote(row[6] != null ? row[6].toString() : null);
            dto.setIsActive(row[7] != null && (Boolean) row[7]);
            dto.setEmployeeName(row[8] != null ? row[8].toString() : null);
            dto.setPositionName(row[9] != null ? row[9].toString() : null);
            
            dtoList.add(dto);
        }
        return dtoList;
    }

    // Thêm mới
    public EmployeePosition createPosition(EmployeePositionDTO dto) {
        EmployeePosition entity = new EmployeePosition();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setPositionId(dto.getPositionId());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDescription(dto.getDescription());
        entity.setNote(dto.getNote());
        entity.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        
        return repository.save(entity);
    }

    // Xóa mềm (Soft Delete)
    public boolean softDeletePosition(UUID id) {
        Optional<EmployeePosition> optional = repository.findById(id);
        if (optional.isPresent()) {
            EmployeePosition entity = optional.get();
            entity.setDeletedAt(LocalDateTime.now());
            entity.setIsActive(false);
            repository.save(entity);
            return true;
        }
        return false;
    }

    // Thêm hàm này vào trong class EmployeePositionService
    public EmployeePosition updatePosition(UUID id, EmployeePositionDTO dto) {
        Optional<EmployeePosition> optional = repository.findById(id);
        if (optional.isPresent()) {
            EmployeePosition entity = optional.get();
            entity.setEmployeeId(dto.getEmployeeId());
            entity.setPositionId(dto.getPositionId());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setDescription(dto.getDescription());
            entity.setNote(dto.getNote());
            entity.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
            entity.setUpdatedAt(LocalDateTime.now());
            return repository.save(entity);
        }
        return null;
    }
}
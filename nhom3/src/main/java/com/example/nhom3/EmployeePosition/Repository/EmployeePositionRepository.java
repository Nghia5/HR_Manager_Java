package com.example.nhom3.EmployeePosition.Repository;

import com.example.nhom3.EmployeePosition.Model.entity.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, UUID> {
    
    // Lấy các bản ghi chưa bị xóa (deleted_at IS NULL)
    List<EmployeePosition> findByDeletedAtIsNullOrderByStartDateDesc();

    // Lấy danh sách kèm theo Tên nhân viên và Tên chức vụ (Dùng Native Query cho dễ giống SQL Server)
    @Query(value = "SELECT ep.id, ep.employee_id, ep.position_id, ep.start_date, ep.end_date, ep.description, ep.note, ep.is_active, " +
                   "e.full_name as employeeName, p.name as positionName " +
                   "FROM employee_positions ep " +
                   "JOIN employees e ON ep.employee_id = e.id " +
                   "JOIN positions p ON ep.position_id = p.id " +
                   "WHERE ep.deleted_at IS NULL " +
                   "ORDER BY ep.start_date DESC", nativeQuery = true)
    List<Object[]> findAllWithNames();
}
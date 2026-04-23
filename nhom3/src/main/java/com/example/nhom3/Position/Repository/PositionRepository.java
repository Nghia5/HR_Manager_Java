package com.example.nhom3.Position.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom3.Position.Model.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {
}
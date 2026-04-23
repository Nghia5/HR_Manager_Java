package com.example.nhom3.Position.Service;

import com.example.nhom3.Position.Model.entity.Position;
import com.example.nhom3.Position.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position getPositionById(UUID id) {
        return positionRepository.findById(id).orElse(null);
    }

    public Position createOrUpdatePosition(Position position) {
        return positionRepository.save(position);
    }
    
    public void deletePosition(UUID id) {
        positionRepository.deleteById(id);
    }
}
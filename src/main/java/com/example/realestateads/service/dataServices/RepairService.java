package com.example.realestateads.service.dataServices;

import com.example.realestateads.entity.RepairType;
import com.example.realestateads.repository.RepairTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final RepairTypeRepository repairTypeRepository;

    public List<RepairType> findAll() {

        return repairTypeRepository.findAll();
    }
}

package com.example.realestateads.repository;

import com.example.realestateads.entity.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, String> {
}
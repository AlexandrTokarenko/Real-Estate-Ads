package com.example.realestateads.repository;

import com.example.realestateads.entity.TypeOfHeating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfHeatingRepository extends JpaRepository<TypeOfHeating, Integer> {
}
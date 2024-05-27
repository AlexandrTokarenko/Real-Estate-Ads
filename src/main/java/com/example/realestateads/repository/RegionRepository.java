package com.example.realestateads.repository;

import com.example.realestateads.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {

    @Query("select r from Region r order by r.title asc")
    List<Region> findAllOrderByTitle();
}
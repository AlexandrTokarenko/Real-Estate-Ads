package com.example.realestateads.repository;

import com.example.realestateads.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query("select count(d) from District d where d.locality.id = ?1")
     int countByLocalityId(int localityId);

    @Query("select d from District d order by d.title asc")
    List<District> findAllOrderByTitle();
}
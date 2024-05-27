package com.example.realestateads.repository;

import com.example.realestateads.dto.FlatRequestDTO;
import com.example.realestateads.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
    @Transactional
    @Modifying
    @Query("update Flat f set f.parking = ?1, f.floor = ?2, f.numberOfFloors = ?3 where f.realty.id = ?4")
    void update(Boolean parking, Integer floor, Integer numberOfFloors, int realtyId);
    Optional<Flat> findByRealty_Id(Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into Flat(floor,number_of_floors,parking,realty_id) values(:#{#flat.floor},:#{#flat.numberOfFloors},:#{#flat.parking}," +
            ":realtyId)",nativeQuery = true)
    void saveFlatRequestDTO(@Param("flat") FlatRequestDTO flat, @Param("realtyId") int realtyId);
}
package com.example.realestateads.repository;

import com.example.realestateads.entity.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {
    @Query("select l from Locality l order by l.title asc ")
    List<Locality> findAllOrderByTitle();
}
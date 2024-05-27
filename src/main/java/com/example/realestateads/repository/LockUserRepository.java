package com.example.realestateads.repository;

import com.example.realestateads.entity.LockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockUserRepository extends JpaRepository<LockUser, Integer> {
}
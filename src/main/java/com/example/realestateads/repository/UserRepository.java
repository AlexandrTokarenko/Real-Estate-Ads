package com.example.realestateads.repository;

import com.example.realestateads.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2")
    void updatePassword(String password, String email);
    @Transactional
    @Modifying
    @Query("update User u set u.activationCode = ?1, u.effectiveTime = ?2 where u.email = ?3")
    void updateActivationCode(String activationCode, Timestamp effectiveTime, String userEmail);

    @Transactional
    @Modifying
    @Query("update User u set u.isVerify = ?1 where u = ?2")
    void updateVerifyEmail(Boolean isVerify, User user);
}
package com.example.realestateads.repository;

import com.example.realestateads.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.email = u.email\s
      where u.email = :email and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String email);

    Optional<Token> findByToken(String token);
}
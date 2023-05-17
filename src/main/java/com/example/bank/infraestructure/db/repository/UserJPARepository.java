package com.example.bank.infraestructure.db.repository;

import com.example.bank.infraestructure.db.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UserDbo, Long> {
}

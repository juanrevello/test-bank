package com.example.bank.infraestructure.db.repository;

import com.example.bank.infraestructure.db.dbo.TransferDbo;
import com.example.bank.infraestructure.db.dbo.WalletDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletJPARepository extends JpaRepository<WalletDbo, Long> {
}

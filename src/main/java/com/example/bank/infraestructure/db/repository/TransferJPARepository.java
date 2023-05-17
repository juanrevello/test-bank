package com.example.bank.infraestructure.db.repository;

import com.example.bank.infraestructure.db.dbo.TransferDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferJPARepository extends JpaRepository<TransferDbo, Long> {
    List<TransferDbo> findByOriginWallet_Id(Long id);
    List<TransferDbo> findByDestinationWallet_Id(Long id);

}
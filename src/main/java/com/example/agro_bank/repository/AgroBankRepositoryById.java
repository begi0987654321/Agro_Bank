package com.example.agro_bank.repository;

import com.example.agro_bank.entity.Agro_BankExcel_by_id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgroBankRepositoryById extends JpaRepository<Agro_BankExcel_by_id, Long> {

}

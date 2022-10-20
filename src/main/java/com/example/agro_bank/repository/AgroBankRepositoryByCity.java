package com.example.agro_bank.repository;

import com.example.agro_bank.entity.Agro_BankExcel_by_city;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgroBankRepositoryByCity extends JpaRepository<Agro_BankExcel_by_city, Long> {

}

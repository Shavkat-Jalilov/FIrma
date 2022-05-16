package com.example.firma.Firma.repozitory;

import com.example.firma.Firma.model.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Firma_repozitory extends JpaRepository<Firma, Integer> {
    boolean existsByNomiAndBoshqaruchiIsmi(String nomi, String boshqaruvchiIsmi);
}

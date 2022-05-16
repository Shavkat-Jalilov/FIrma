package com.example.firma.Firma.repozitory;

import com.example.firma.Firma.model.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ishchi_repozitory extends JpaRepository<Ishchi, Integer> {
        boolean existsByTelRaqam(String telRaqam);
}

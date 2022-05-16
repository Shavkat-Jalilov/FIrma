package com.example.firma.Firma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Firma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Firma nomini kiriting")
    private String nomi;
    @Column(nullable = false)
    @NotNull(message = "Boshqaruvchi ismini")
    private String boshqaruchiIsmi;
}

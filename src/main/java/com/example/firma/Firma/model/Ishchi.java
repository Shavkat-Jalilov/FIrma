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
public class Ishchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotNull(message = "Ismni kiriting")
    private String ismi;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Telefon raqamni kiriting")
    private String telRaqam;
    @Column(nullable = false)
    @NotNull(message = "Maoshini kiriting")
    private Integer maoshi;
    @Column(nullable = false)
    @NotNull(message = "Lavozimini kiriting")
    private String lavoimi;
    @Column(nullable = false)
    @NotNull(message = "Manzilini kiriting")
    private String manzili;

    @ManyToOne
    Firma firma;
}

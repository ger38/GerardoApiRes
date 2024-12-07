package com.Gerardo.ApiRes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;

@AllArgsConstructor
@Entity
@Data
@Table(name = "Personas")
public class Persona {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Medico medico;

    public Persona () {

    }
}


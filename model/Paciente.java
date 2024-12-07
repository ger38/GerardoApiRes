package com.Gerardo.ApiRes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToOne
    private Persona persona;
    private String numeroFicha;

}

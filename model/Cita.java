package com.Gerardo.ApiRes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "Turnos")
public class Cita {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private Date fecha;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    private String descripcion;
}


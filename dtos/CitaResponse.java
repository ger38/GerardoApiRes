package com.Gerardo.ApiRes.dtos;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.util.Date;

@Data
public class CitaResponse {

    private Long id;
    private Date fecha;
    private MedicoResponse medico;
    private PacienteResponse paciente;
    private String descripcion;
}

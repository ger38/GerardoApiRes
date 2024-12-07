package com.Gerardo.ApiRes.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CitaRequest {

    private Long id;
    private Date fecha;
    private Long medicoId;
    private Long pacienteId;
    private String descripcion;
}

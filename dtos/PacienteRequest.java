package com.Gerardo.ApiRes.dtos;

import lombok.Data;

@Data
public class PacienteRequest {

    private Long id;
    private String numeroFicha;
    private long personaId;
}

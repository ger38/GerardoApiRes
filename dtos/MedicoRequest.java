package com.Gerardo.ApiRes.dtos;

import lombok.Data;

@Data
public class MedicoRequest {

    private Long id;
    private Long personaId;
    private String especializacion;
    private String titulo;
}

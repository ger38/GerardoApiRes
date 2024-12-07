package com.Gerardo.ApiRes.dtos;

import lombok.Data;

@Data
public class PacienteResponse {

    private Long id;
    private Long idpersona;
    private String nombre;
    private String numeroFicha;
}

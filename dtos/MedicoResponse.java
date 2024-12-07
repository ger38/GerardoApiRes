package com.Gerardo.ApiRes.dtos;

import lombok.Data;

@Data
public class MedicoResponse {

    private Long id;
    private Long idpersona;
    private String nombre;
    private String especializacion;
    private String titulo;
}

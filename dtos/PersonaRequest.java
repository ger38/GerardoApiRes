package com.Gerardo.ApiRes.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonaRequest {

    private long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
}

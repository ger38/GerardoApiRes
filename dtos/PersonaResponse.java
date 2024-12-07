package com.Gerardo.ApiRes.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class PersonaResponse {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;

}

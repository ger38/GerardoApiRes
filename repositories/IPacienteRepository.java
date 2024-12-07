package com.Gerardo.ApiRes.repositories;

import com.Gerardo.ApiRes.model.Paciente;
import com.Gerardo.ApiRes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
    List<Paciente> findByNumeroFicha(String numeroFicha);
}


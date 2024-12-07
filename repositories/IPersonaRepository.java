package com.Gerardo.ApiRes.repositories;

import com.Gerardo.ApiRes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public  interface IPersonaRepository extends JpaRepository<Persona,Long>{

    List<Persona> findByDni(String dni);
}

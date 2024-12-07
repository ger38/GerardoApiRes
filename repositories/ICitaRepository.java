package com.Gerardo.ApiRes.repositories;

import com.Gerardo.ApiRes.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ICitaRepository extends JpaRepository<Cita,Long> {

}

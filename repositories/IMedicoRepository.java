package com.Gerardo.ApiRes.repositories;

import com.Gerardo.ApiRes.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IMedicoRepository extends JpaRepository<Medico,Long> {
    List<Medico> findByTitulo(String titulo);
}

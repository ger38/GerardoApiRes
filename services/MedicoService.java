package com.Gerardo.ApiRes.services;


import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Gerardo.ApiRes.dtos.MedicoRequest;
import com.Gerardo.ApiRes.dtos.MedicoResponse;
import com.Gerardo.ApiRes.dtos.MedicosResponse;
import com.Gerardo.ApiRes.model.Medico;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.mappers.MedicoMapper;
import com.Gerardo.ApiRes.mappers.PersonaMapper;
import com.Gerardo.ApiRes.repositories.IMedicoRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
//import javax.transaction.Transactional;


import java.util.NoSuchElementException;
@Service
@Getter
@Setter
public class MedicoService {

    @Autowired
    IMedicoRepository repository;
    @Autowired
    MedicoMapper mapper;
    @Autowired
    PersonaMapper personaMapper;

    @Transactional
    public ResponseEntity<MedicosResponse> getMedicos(){
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body(mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }
        return response;

    }

    @Transactional
    public ResponseEntity<MedicoResponse> getById(Long id){

        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body(mapper.ModelToResponse(repository.findById(id).get()));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("Medico no encontrado con el id: " + id);
        }
        return response;

    }

    public Medico getMedicoById(Long id){
        Medico medico = null;
        try {
            medico = repository.findById(id).get();
        }catch (Exception e){
            System.out.println("El medico no existe.");
        }
        return medico;
    }
    @Transactional
    public ResponseEntity saveMedico(MedicoRequest medico){
        ResponseEntity response = null;
        try {
            if (repository.findByTitulo(medico.getTitulo()).isEmpty()) {
                Medico newMedico = repository.save(mapper.RequestToModel(medico));
                response = ResponseEntity.ok().body("Titulo: " + newMedico.getTitulo() + " Especialización: " + newMedico.getEspecializacion() + ". Registrado correctamente.");
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error al guardar al medico.");
        }
        return response;


    }

    @Transactional
    public ResponseEntity<MedicoResponse> updateById(MedicoRequest request, Long id) {
        ResponseEntity response = null;
        try {
            Medico medico = repository.findById(id).get();
            medico.setTitulo(request.getTitulo());
            medico.setEspecializacion(request.getEspecializacion());
            repository.save(medico);
            response = ResponseEntity.ok().body(mapper.ModelToResponse(medico));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe un medico con el id: " + id);
        }

        return response;
    }

    @Transactional
    public ResponseEntity<String> deleteById(Long id) {

        ResponseEntity response = null;
        try {
            if(repository.existsById(id)){
                repository.deleteById(id);
                response = ResponseEntity.ok().body("Medico eliminado con el id: " + id);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe un medico con el id: " + id);
        }
        return response;
    }

}


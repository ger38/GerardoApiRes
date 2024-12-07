package com.Gerardo.ApiRes.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import com.Gerardo.ApiRes.dtos.CitaRequest;
import com.Gerardo.ApiRes.dtos.CitaResponse;
import com.Gerardo.ApiRes.dtos.CitasResponse;
import com.Gerardo.ApiRes.model.Cita;
import com.Gerardo.ApiRes.mappers.CitaMapper;
import com.Gerardo.ApiRes.mappers.MedicoMapper;
import com.Gerardo.ApiRes.mappers.PacienteMapper;
import com.Gerardo.ApiRes.repositories.ICitaRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
//import javax.transaction.Transactional;

@Getter
@Service
@Setter

public class CitaService {

    @Autowired
    ICitaRepository repository;
    @Autowired
    CitaMapper mapper;
    @Autowired
    MedicoService medicoService;
    @Autowired
    PacienteService pacienteService;


    @Transactional
    public ResponseEntity<CitasResponse> getCitas(){

        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body( mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @Transactional
    public ResponseEntity<CitaResponse> getById(Long id){
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok(mapper.ModelToResponse(repository.findById(id).get()));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("Cita no encontrada con el id: " + id);
        }
        return response;

    }
    @Transactional
    public ResponseEntity saveCita(CitaRequest cita){
        ResponseEntity response = null;
        try {
            if (medicoService.getRepository().existsById(cita.getMedicoId()) && pacienteService.getRepository().existsById(cita.getPacienteId())){
                repository.save(mapper.RequestToModel(cita));
                response = ResponseEntity.ok().body("Cita registrada correctamente");
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Error al guardar la cita. Algunos de los id no existe");
        }
        return response;
    }

    @Transactional
    public ResponseEntity<CitaResponse> updateById(CitaRequest request, Long id) {
        ResponseEntity response = null;
        try {
            Cita cita = repository.findById(id).get();
            cita.setFecha(request.getFecha());
            cita.setDescripcion(request.getDescripcion());
            cita.setPaciente(pacienteService.getPacienteById(request.getPacienteId()));
            cita.setMedico(medicoService.getMedicoById(request.getMedicoId()));
            repository.save(cita);
            response = ResponseEntity.ok().body(mapper.ModelToResponse(cita));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe una cita con el id: " + id);
        }
        return response;
    }

    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        ResponseEntity response = null;
        try {
            if(repository.existsById(id)){
                repository.deleteById(id);
                response = ResponseEntity.ok().body("Cita eliminada con el id: " + id);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe una cita con el id: " + id);
        }
        return response;
    }

}

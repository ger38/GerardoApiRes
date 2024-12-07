package com.Gerardo.ApiRes.services;

import com.Gerardo.ApiRes.mappers.PersonaMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Gerardo.ApiRes.dtos.PacienteRequest;
import com.Gerardo.ApiRes.dtos.PacienteResponse;
import com.Gerardo.ApiRes.dtos.PacientesResponse;
import com.Gerardo.ApiRes.model.Paciente;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.mappers.PacienteMapper;
import com.Gerardo.ApiRes.mappers.PersonaMapper;
import com.Gerardo.ApiRes.repositories.IPacienteRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Getter
@Service
@Setter
public class PacienteService {

    @Autowired
    IPacienteRepository repository;
    @Autowired
    PacienteMapper mapper;
    @Autowired
    PersonaMapper personaMapper;


    @Transactional
    public ResponseEntity<PacientesResponse> getPacientes(){
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body(mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }

    @Transactional
    public ResponseEntity<PacienteResponse> getById(Long id){
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok(mapper.ModelToResponse(repository.findById(id).get()));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Paciente no encontrado con el id: " + id);
        }
        return response;

    }

    public Paciente getPacienteById(Long id){

        Paciente paciente = null;
        try {
            paciente = repository.findById(id).get();
        }catch (Exception e){
            System.out.println("El paciente no existe");
        }
        return paciente;
    }

    @Transactional
    public ResponseEntity savePaciente(PacienteRequest paciente){
        ResponseEntity response = null;
        try {
            if (repository.findByNumeroFicha(paciente.getNumeroFicha()).isEmpty()){
                repository.save(mapper.RequestToModel(paciente));
                response = ResponseEntity.ok().body("Ficha número : " + paciente.getNumeroFicha() + " archivada.");
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("La ficha: " + paciente.getNumeroFicha() + "ya esta asignada a otro paciente.");
        }

        return response;

    }

    @Transactional
    public ResponseEntity<PacienteResponse> updateById(PacienteRequest request, Long id) {
        ResponseEntity response = null;
        try {
            Paciente paciente = repository.findById(id).get();
            paciente.setNumeroFicha(request.getNumeroFicha());
            repository.save(paciente);
            response = ResponseEntity.ok(mapper.ModelToResponse(paciente));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existen pacientes con el id: " + id);
        }
        return response;
    }

    @Transactional
    public ResponseEntity<String> deleteById(Long id) {
        ResponseEntity response = null;
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                response = ResponseEntity.ok("Paciente con el id: " + id + " eliminado");
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe un paciente con el id: " + id);
        }
        return response;
    }
}


package com.Gerardo.ApiRes.services;

import com.Gerardo.ApiRes.dtos.PersonaRequest;
import com.Gerardo.ApiRes.dtos.PersonaResponse;
import com.Gerardo.ApiRes.dtos.PersonasResponse;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.mappers.PersonaMapper;
import com.Gerardo.ApiRes.repositories.IPersonaRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class PersonaService {


    @Autowired
    IPersonaRepository repository;
    @Autowired
    PersonaMapper mapper;

    @Transactional
    public ResponseEntity<PersonasResponse> getPersonas(){
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body(mapper.ListToResponse(repository.findAll()));
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().build();
        }
        return response;
    }
    @Transactional
    public ResponseEntity<PersonaResponse> getById(Long id) {
        ResponseEntity response = null;
        try {
            response = ResponseEntity.ok().body(mapper.ModelToResponse(repository.findById(id).get()));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("Persona no encontrada con el id: " + id);
        }
        return response;
    }
    public Persona getPersonaById(Long id){
        Persona persona = null;
        try {
            persona = repository.findById(id).get();
        }catch (Exception e){
            System.out.println("La persona no existe");
        }
        return persona;
    }
    @Transactional
    public ResponseEntity savePersona(PersonaRequest request) {
        ResponseEntity response = null;
        try {
            if (repository.findByDni(request.getDni()).isEmpty()){
                Persona p = repository.save(mapper.RequestToModel(request));
                response = ResponseEntity.ok().body(p.getNombre() + " guardado.");
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("La persona con el DNI especificado ya existe.");
        }

        return response;
    }
    @Transactional
    public ResponseEntity<PersonaResponse> updateById(PersonaRequest request, Long id) {
        ResponseEntity response = null;
        try {
            Persona persona = repository.findById(id).get();
            persona.setDni(request.getDni());
            persona.setNombre(request.getNombre());
            persona.setFechaNacimiento(request.getFechaNacimiento());
            repository.save(persona);
            response = ResponseEntity.ok().body(mapper.ModelToResponse(persona));
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existen personas con el id: " + id);
        }
        return response;
    }
    @Transactional
    public ResponseEntity<String> deletePersonaById(Long id) {
        ResponseEntity response = null;
        try {
            if(repository.existsById(id)){
                repository.deleteById(id);
                response = ResponseEntity.ok().body("Persona eliminada con el id: " + id);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            response = ResponseEntity.badRequest().body("No existe una persona con el id: " + id);
        }
        return response;
    }


}

package com.Gerardo.ApiRes.mappers;

import com.Gerardo.ApiRes.dtos.PersonaRequest;
import com.Gerardo.ApiRes.dtos.PersonaResponse;
import com.Gerardo.ApiRes.dtos.PersonasResponse;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.mappers.PersonaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;



@Service
public class PersonaMapper {

    public Persona RequestToModel(PersonaRequest request){
        Persona persona = new Persona();
        persona.setId(request.getId());
        persona.setNombre(request.getNombre());
        persona.setFechaNacimiento(request.getFechaNacimiento());
        persona.setDni(request.getDni());
        return persona;
    }

    public PersonasResponse ListToResponse(List<Persona> personas){
        PersonasResponse response = new PersonasResponse();
        List<PersonaResponse> responseList = new ArrayList<PersonaResponse>();
        for (Persona model: personas) {
            responseList.add(ModelToResponse(model));
        }
        response.setPersonas(responseList);
        return response;
    }

    public PersonaResponse ModelToResponse(Persona model){
        PersonaResponse response = new PersonaResponse();
        response.setId(model.getId());
        response.setDni(model.getDni());
        response.setFechaNacimiento(model.getFechaNacimiento());
        response.setNombre(model.getNombre());
        return response;
    }

    public PersonaRequest ModelToRequest(Persona model){
        PersonaRequest response = new PersonaRequest();
        response.setDni(model.getDni());
        response.setFechaNacimiento(model.getFechaNacimiento());
        response.setNombre(model.getNombre());
        return response;
    }

    public List<Persona> ResponseToListModel(PersonasResponse response){
        List<Persona> personasList = new ArrayList<>();
        List<PersonaResponse> personaResponsesList = response.getPersonas();
        for (PersonaResponse personaResponse : personaResponsesList) {
            Persona p = new Persona();
            p.setId(personaResponse.getId());
            p.setNombre(personaResponse.getNombre());
            p.setDni(personaResponse.getDni());
            p.setFechaNacimiento(personaResponse.getFechaNacimiento());
            personasList.add(p);
        }
        return personasList;
    }



}

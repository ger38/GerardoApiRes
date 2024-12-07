package com.Gerardo.ApiRes.mappers;

import com.Gerardo.ApiRes.dtos.PacienteRequest;
import com.Gerardo.ApiRes.dtos.PacienteResponse;
import com.Gerardo.ApiRes.dtos.PacientesResponse;
import com.Gerardo.ApiRes.model.Paciente;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class PacienteMapper {

    @Autowired
    PersonaService personaService;
    public Paciente RequestToModel(PacienteRequest request){
        Paciente paciente = new Paciente();
        paciente.setNumeroFicha(request.getNumeroFicha());
        paciente.setPersona(personaService.getPersonaById(request.getPersonaId()));
        return paciente;
    }

    public PacientesResponse ListToResponse(List<Paciente> pacientes){
        PacientesResponse response = new PacientesResponse();
        List<PacienteResponse> responseList = new ArrayList<PacienteResponse>();
        for (Paciente model: pacientes) {
            responseList.add(ModelToResponse(model));
        }
        response.setPacientes(responseList);
        return response;
    }

    public PacienteResponse ModelToResponse(Paciente model){
        PacienteResponse response = new PacienteResponse();
        response.setId(model.getId());
        response.setIdpersona(model.getPersona().getId());
        response.setNombre(model.getPersona().getNombre());
        response.setNumeroFicha(model.getNumeroFicha());
        return response;
    }

    public PacienteRequest ModelToRequest(Paciente model){
        PacienteRequest request = new PacienteRequest();
        request.setId(model.getId());
        request.setNumeroFicha(model.getNumeroFicha());
        return request;
    }
}


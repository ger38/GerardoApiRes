package com.Gerardo.ApiRes.mappers;


import com.Gerardo.ApiRes.dtos.*;
import com.Gerardo.ApiRes.model.Cita;
import com.Gerardo.ApiRes.services.MedicoService;
import com.Gerardo.ApiRes.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CitaMapper {

    @Autowired
    PacienteService pacienteService;
    @Autowired
    MedicoService medicoService;

    public CitasResponse ListToResponse(List<Cita> modelList){
        CitasResponse response = new CitasResponse();
        List<CitaResponse> responseList = new ArrayList<>();
        for (Cita cita: modelList) {
            responseList.add(ModelToResponse(cita));
        }
        response.setCitas(responseList);
        return response;
    }
    public CitaResponse ModelToResponse(Cita cita){
        CitaResponse response = new CitaResponse();
        response.setId(cita.getId());
        response.setFecha(cita.getFecha());
        response.setDescripcion(cita.getDescripcion());
        response.setMedico(medicoService.getMapper().ModelToResponse(cita.getMedico()));
        response.setPaciente(pacienteService.getMapper().ModelToResponse(cita.getPaciente()));
        return response;
    }
    public Cita RequestToModel(CitaRequest request){
        Cita cita = new Cita();
        cita.setId(request.getId());
        cita.setDescripcion(request.getDescripcion());
        cita.setFecha(request.getFecha());
        cita.setMedico(medicoService.getMedicoById(request.getMedicoId()));
        cita.setPaciente(pacienteService.getPacienteById(request.getPacienteId()));
        return cita;
    }

}


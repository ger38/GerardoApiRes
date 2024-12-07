package com.Gerardo.ApiRes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.Gerardo.ApiRes.dtos.PacienteResponse;
import com.Gerardo.ApiRes.dtos.PacientesResponse;
import com.Gerardo.ApiRes.dtos.PacienteRequest;
import com.Gerardo.ApiRes.model.Paciente;
import com.Gerardo.ApiRes.services.PacienteService;
import com.Gerardo.ApiRes.controller.PacienteController;
import org.springframework.stereotype.Controller;
import com.Gerardo.ApiRes.mappers.PacienteMapper;
import com.Gerardo.ApiRes.repositories.IPacienteRepository;

@RestController
@RequestMapping("/paciente")

public class PacienteController {

    @Autowired
    PacienteService service;
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public ResponseEntity<PacientesResponse> getPacientes(){
        return service.getPacientes();
    }
    @PostMapping("/")
    public ResponseEntity savePaciente(@RequestBody PacienteRequest paciente){
        return service.savePaciente(paciente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updateById(@PathVariable("id") Long id, @RequestBody PacienteRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}


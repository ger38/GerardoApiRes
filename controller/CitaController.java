package com.Gerardo.ApiRes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Gerardo.ApiRes.dtos.*;
import com.Gerardo.ApiRes.model.Cita;
import com.Gerardo.ApiRes.model.Medico;
import com.Gerardo.ApiRes.services.CitaService;
import com.Gerardo.ApiRes.services.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.Gerardo.ApiRes.controller.CitaController;
import com.Gerardo.ApiRes.mappers.CitaMapper;
import com.Gerardo.ApiRes.repositories.ICitaRepository;


@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    CitaService service;
    @GetMapping("/")
    public ResponseEntity<CitasResponse> getCitas(){
        return service.getCitas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> getCitaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping("/")
    public ResponseEntity saveCita(@RequestBody CitaRequest cita){
        return service.saveCita(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> updateById(@PathVariable("id") Long id, @RequestBody CitaRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}


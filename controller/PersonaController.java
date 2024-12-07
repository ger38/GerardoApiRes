package com.Gerardo.ApiRes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.Gerardo.ApiRes.dtos.PersonaRequest;
import com.Gerardo.ApiRes.dtos.PersonaResponse;
import com.Gerardo.ApiRes.dtos.PersonasResponse;
import com.Gerardo.ApiRes.model.Persona;
import com.Gerardo.ApiRes.controller.PersonaController;
import com.Gerardo.ApiRes.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.Gerardo.ApiRes.mappers.PersonaMapper;
import com.Gerardo.ApiRes.repositories.IPersonaRepository;

import java.util.List;
import java.util.Optional;
@Service
@RestController
@RequestMapping("/personas")
@CrossOrigin
public class PersonaController {

    @Autowired
    PersonaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @GetMapping("/")
    public ResponseEntity<PersonasResponse> getPersonas(){
        return service.getPersonas();
    }

    @PostMapping("/")
    public ResponseEntity<PersonaResponse> savePersona(@RequestBody PersonaRequest persona){
        return service.savePersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> updateById(@PathVariable("id") Long id, @RequestBody PersonaRequest request ){
        return service.updateById(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deletePersonaById(id);
    }

}


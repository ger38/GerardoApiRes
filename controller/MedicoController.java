package com.Gerardo.ApiRes.controller;

import com.Gerardo.ApiRes.dtos.*;
import com.Gerardo.ApiRes.model.Medico;
import com.Gerardo.ApiRes.services.MedicoService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Gerardo.ApiRes.controller.MedicoController;
import java.sql.SQLException;
import com.Gerardo.ApiRes.mappers.MedicoMapper;
import com.Gerardo.ApiRes.repositories.IMedicoRepository;


@RequestMapping("/medico")
@Controller
@RestController

public class MedicoController {
    @Autowired
    MedicoService service;
    @GetMapping("/")
    public ResponseEntity<MedicosResponse> getMedico(){
        return service.getMedicos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @PostMapping("/")
    public ResponseEntity saveMedico(@RequestBody MedicoRequest medico){
        return service.saveMedico(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateById(@PathVariable("id") Long id, @RequestBody MedicoRequest request ){
        return service.updateById(request, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return service.deleteById(id);
    }
}


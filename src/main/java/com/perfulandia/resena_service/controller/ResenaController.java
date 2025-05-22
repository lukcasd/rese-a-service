package com.perfulandia.resena_service.controller;

import com.perfulandia.resena_service.model.Resena;
import com.perfulandia.resena_service.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService service;

    @PostMapping
    public Resena crear(@RequestBody Resena resena) {
        return service.guardar(resena);
    }

    @GetMapping
    public List<Resena> listar() {
        return service.listarTodas();
    }
}
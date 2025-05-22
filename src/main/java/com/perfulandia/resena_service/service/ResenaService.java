package com.perfulandia.resena_service.service;

import com.perfulandia.resena_service.model.Resena;
import com.perfulandia.resena_service.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository repository;

    public Resena guardar(Resena resena) {
        return repository.save(resena);
    }

    public List<Resena> listarTodas() {
        return repository.findAll();
    }
}
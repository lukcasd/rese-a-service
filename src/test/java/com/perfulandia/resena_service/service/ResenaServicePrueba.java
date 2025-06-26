package com.perfulandia.resena_service.service;

import com.perfulandia.resena_service.model.Resena;
import com.perfulandia.resena_service.repository.ResenaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResenaServicePrueba {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarResena() {
        Resena nueva = new Resena();
        nueva.setAutor("Ronald");
        nueva.setComentario("Excelente producto");
        nueva.setCalificacion(5);
        nueva.setFecha(LocalDate.now());
        nueva.setIdProducto(123);

        Resena guardada = new Resena();
        guardada.setId(1);
        guardada.setAutor("Ronald");
        guardada.setComentario("Excelente producto");
        guardada.setCalificacion(5);
        guardada.setFecha(LocalDate.now());
        guardada.setIdProducto(123);

        when(resenaRepository.save(nueva)).thenReturn(guardada);

        Resena resultado = resenaService.guardar(nueva);

        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getComentario()).isEqualTo("Excelente producto");

        verify(resenaRepository).save(nueva);
    }

    @Test
    void testListarTodas() {
        Resena r1 = new Resena();
        r1.setId(1);
        r1.setAutor("Ana");
        r1.setComentario("Muy bueno");
        r1.setCalificacion(4);
        r1.setFecha(LocalDate.now());
        r1.setIdProducto(101);

        Resena r2 = new Resena();
        r2.setId(2);
        r2.setAutor("Luis");
        r2.setComentario("No me gustó");
        r2.setCalificacion(2);
        r2.setFecha(LocalDate.now());
        r2.setIdProducto(102);

        when(resenaRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Resena> resultado = resenaService.listarTodas();

        assertThat(resultado).hasSize(2).contains(r1, r2);
        verify(resenaRepository).findAll();
    }
}
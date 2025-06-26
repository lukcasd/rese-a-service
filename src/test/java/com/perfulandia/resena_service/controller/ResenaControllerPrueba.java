package com.perfulandia.resena_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.resena_service.model.Resena;
import com.perfulandia.resena_service.service.ResenaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResenaController.class)
class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService resenaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearResena() throws Exception {
        Resena nueva = new Resena();
        nueva.setAutor("Ronald");
        nueva.setComentario("Buen producto");
        nueva.setCalificacion(4);
        nueva.setFecha(LocalDate.now());
        nueva.setIdProducto(101);

        Resena guardada = new Resena();
        guardada.setId(1);
        guardada.setAutor("Ronald");
        guardada.setComentario("Buen producto");
        guardada.setCalificacion(4);
        guardada.setFecha(LocalDate.now());
        guardada.setIdProducto(101);

        Mockito.when(resenaService.guardar(any(Resena.class))).thenReturn(guardada);

        mockMvc.perform(post("/api/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comentario").value("Buen producto"));
    }

    @Test
    void testListarResenas() throws Exception {
        Resena r1 = new Resena();
        r1.setId(1);
        r1.setAutor("Ana");
        r1.setComentario("Muy bueno");
        r1.setCalificacion(5);
        r1.setFecha(LocalDate.now());
        r1.setIdProducto(123);

        Resena r2 = new Resena();
        r2.setId(2);
        r2.setAutor("Luis");
        r2.setComentario("Regular");
        r2.setCalificacion(3);
        r2.setFecha(LocalDate.now());
        r2.setIdProducto(124);

        Mockito.when(resenaService.listarTodas()).thenReturn(Arrays.asList(r1, r2));

        mockMvc.perform(get("/api/resenas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].autor").value("Ana"))
                .andExpect(jsonPath("$[1].autor").value("Luis"));
    }
}

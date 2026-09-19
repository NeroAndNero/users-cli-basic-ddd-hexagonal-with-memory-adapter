/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jcaa.udec.collections.adapter.persistence.memory.ProyectoMemoryRepository;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ASUS
 */
public class ListarProyectosServiceTest {

    private ProyectoCommand comando(String codigo) {
        return new ProyectoCommand(codigo, "ORION", "Plataforma Orion",
                LocalDate.of(2026, 1, 15), null, "EN_CURSO", "EMP-001");
    }

    @Test
    void listaVaciaAlInicio() {
        assertTrue(new ListarProyectosService(new ProyectoMemoryRepository()).ejecutar().isEmpty());
    }

    @Test
    void listaTodosLosProyectosRegistrados() {
        ProyectoRepositoryPort repo = new ProyectoMemoryRepository();
        CrearProyectoService crear = new CrearProyectoService(repo);
        
        crear.ejecutar(comando("PRY-001"));
        crear.ejecutar(comando("PRY-002"));
        
        assertEquals(2, new ListarProyectosService(repo).ejecutar().size());
    }    
}

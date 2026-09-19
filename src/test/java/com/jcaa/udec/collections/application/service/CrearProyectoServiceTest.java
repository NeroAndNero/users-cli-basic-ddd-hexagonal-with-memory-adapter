/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.udec.collections.adapter.persistence.memory.ProyectoMemoryRepository;
import com.jcaa.udec.collections.domain.core.exception.ProyectoYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Proyecto;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ASUS
 */
public class CrearProyectoServiceTest {

    private CrearProyectoService servicio;

    @BeforeEach
    void iniciar() {
        servicio = new CrearProyectoService(new ProyectoMemoryRepository());
    }

    private ProyectoCommand comando(String codigo) {
        return new ProyectoCommand(codigo, "ORION", "Plataforma Orion",
                LocalDate.of(2026, 1, 15), null, "EN_CURSO", "EMP-001");
    }

    @Test
    void creaUnProyectoNuevo() {
        Proyecto creado = servicio.ejecutar(comando("PRY-001"));
        assertEquals("PRY-001", creado.getId().valor());
    }

    @Test
    void rechazaCodigoDuplicado() {
        servicio.ejecutar(comando("PRY-001"));
        assertThrows(ProyectoYaExisteException.class, () -> servicio.ejecutar(comando("PRY-001")));
    }    
}

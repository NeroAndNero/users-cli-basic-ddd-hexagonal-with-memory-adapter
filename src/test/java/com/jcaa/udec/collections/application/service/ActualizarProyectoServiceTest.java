/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.udec.collections.adapter.persistence.memory.ProyectoMemoryRepository;
import com.jcaa.udec.collections.domain.core.exception.ProyectoNoEncontradoException;
import com.jcaa.udec.collections.domain.core.valueobject.EstadoProyecto;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ASUS
 */
public class ActualizarProyectoServiceTest {

    private ProyectoCommand comando(String estado, LocalDate fin) {
        return new ProyectoCommand("PRY-001", "ORION", "Plataforma Orion",
                LocalDate.of(2026, 1, 15), fin, estado, "EMP-001");
    }

    @Test
    void actualizaProyectoExistente() {
        ProyectoRepositoryPort repo = new ProyectoMemoryRepository();
        new CrearProyectoService(repo).ejecutar(comando("EN_CURSO", null));

        new ActualizarProyectoService(repo).ejecutar(comando("FINALIZADO", LocalDate.of(2026, 6, 30)));

        assertEquals(EstadoProyecto.FINALIZADO,
                new BuscarProyectoService(repo).ejecutar("PRY-001").getEstado());
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        ActualizarProyectoService servicio = new ActualizarProyectoService(new ProyectoMemoryRepository());
        assertThrows(ProyectoNoEncontradoException.class,
                () -> servicio.ejecutar(comando("EN_CURSO", null)));
    }    
}

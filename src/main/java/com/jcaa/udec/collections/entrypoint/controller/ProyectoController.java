/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.ActualizarProyectoService;
import com.jcaa.udec.collections.application.service.BuscarProyectoService;
import com.jcaa.udec.collections.application.service.CrearProyectoService;
import com.jcaa.udec.collections.application.service.EliminarProyectoService;
import com.jcaa.udec.collections.application.service.ListarProyectosService;
import com.jcaa.udec.collections.application.service.ProyectoCommand;
import com.jcaa.udec.collections.domain.core.model.Proyecto;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class ProyectoController {

    private final CrearProyectoService crearService;
    private final BuscarProyectoService buscarService;
    private final ActualizarProyectoService actualizarService;
    private final EliminarProyectoService eliminarService;
    private final ListarProyectosService listarService;

    public ProyectoController(CrearProyectoService crearService,
                              BuscarProyectoService buscarService,
                              ActualizarProyectoService actualizarService,
                              EliminarProyectoService eliminarService,
                              ListarProyectosService listarService) {
        this.crearService = crearService;
        this.buscarService = buscarService;
        this.actualizarService = actualizarService;
        this.eliminarService = eliminarService;
        this.listarService = listarService;
    }

    public Proyecto crear(String codigo, String nombreClave, String denominacion,
                          String fechaInicio, String fechaFin, String estado, String promotorId) {
        return crearService.ejecutar(comando(codigo, nombreClave, denominacion,
                fechaInicio, fechaFin, estado, promotorId));
    }

    public Proyecto buscar(String codigo) {
        return buscarService.ejecutar(codigo);
    }

    public Proyecto actualizar(String codigo, String nombreClave, String denominacion,
                               String fechaInicio, String fechaFin, String estado, String promotorId) {
        return actualizarService.ejecutar(comando(codigo, nombreClave, denominacion,
                fechaInicio, fechaFin, estado, promotorId));
    }

    public void eliminar(String codigo) {
        eliminarService.ejecutar(codigo);
    }

    public List<Proyecto> listar() {
        return listarService.ejecutar();
    }

    private ProyectoCommand comando(String codigo, String nombreClave, String denominacion,
                                    String fechaInicio, String fechaFin, String estado,
                                    String promotorId) {
        return new ProyectoCommand(codigo, nombreClave, denominacion,
                parsearFecha(fechaInicio), parsearFecha(fechaFin), estado, promotorId);
    }

    private LocalDate parsearFecha(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(texto.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha no válida: " + texto + ". Use el formato AAAA-MM-DD.");
        }
    }    
}

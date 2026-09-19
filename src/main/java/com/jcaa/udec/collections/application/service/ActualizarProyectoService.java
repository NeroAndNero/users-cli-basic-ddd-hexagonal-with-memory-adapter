/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.ProyectoNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
/**
 *
 * @author ASUS
 */
public class ActualizarProyectoService {
    private final ProyectoRepositoryPort repositorio;

    public ActualizarProyectoService(ProyectoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    public Proyecto ejecutar(ProyectoCommand comando) {
        Proyecto actualizado = comando.aProyecto();

        if (!repositorio.existePorId(actualizado.getId())) {
            throw new ProyectoNoEncontradoException(actualizado.getId());
        }

        repositorio.guardar(actualizado);
        return actualizado;
    }    
}

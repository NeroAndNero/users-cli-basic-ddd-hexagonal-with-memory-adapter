/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.ProyectoNoEncontradoException;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
/**
 *
 * @author ASUS
 */
public class EliminarProyectoService {
    private final ProyectoRepositoryPort repositorio;

    public EliminarProyectoService(ProyectoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(String codigo) {
        ProyectoId id = new ProyectoId(codigo);
        if (!repositorio.eliminarPorId(id)) {
            throw new ProyectoNoEncontradoException(id);
        }
    }    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.ProyectoNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
/**
 *
 * @author ASUS
 */
public class BuscarProyectoService {
    private final ProyectoRepositoryPort repositorio;

    public BuscarProyectoService(ProyectoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    public Proyecto ejecutar(String codigo) {
        ProyectoId id = new ProyectoId(codigo);
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new ProyectoNoEncontradoException(id));
    }    
}

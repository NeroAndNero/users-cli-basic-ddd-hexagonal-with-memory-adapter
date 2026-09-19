/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.exception;

import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
/**
 *
 * @author ASUS
 */
public class ProyectoNoEncontradoException extends RuntimeException {
    public ProyectoNoEncontradoException(ProyectoId id) {
        super("No existe un proyecto con el código: " + id);
    }    
}

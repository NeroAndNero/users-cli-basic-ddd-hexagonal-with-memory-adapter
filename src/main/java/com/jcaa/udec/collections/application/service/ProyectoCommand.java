/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.core.valueobject.DenominacionComercial;
import com.jcaa.udec.collections.domain.core.valueobject.EstadoProyecto;
import com.jcaa.udec.collections.domain.core.valueobject.NombreClave;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import java.time.LocalDate;
/**
 *
 * @author ASUS
 */
public record ProyectoCommand(String codigo,
                              String nombreClave,
                              String denominacionComercial,
                              LocalDate fechaInicio,
                              LocalDate fechaFin,
                              String estado,
                              String promotorId) {

    public Proyecto aProyecto() {
        return new Proyecto(
                new ProyectoId(codigo),
                new NombreClave(nombreClave),
                new DenominacionComercial(denominacionComercial),
                fechaInicio,
                fechaFin,
                EstadoProyecto.desde(estado),
                promotorId);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.DenominacionComercial;
import com.jcaa.udec.collections.domain.core.valueobject.EstadoProyecto;
import com.jcaa.udec.collections.domain.core.valueobject.NombreClave;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author ASUS
 */
public class Proyecto {
    private final ProyectoId id;
    private final NombreClave nombreClave;
    private final DenominacionComercial denominacionComercial;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final EstadoProyecto estado;
    private final String promotorId;

    public Proyecto(ProyectoId id,
                    NombreClave nombreClave,
                    DenominacionComercial denominacionComercial,
                    LocalDate fechaInicio,
                    LocalDate fechaFin,
                    EstadoProyecto estado,
                    String promotorId) {
        this.id = requerido(id, "El código del proyecto es obligatorio.");
        this.nombreClave = requerido(nombreClave, "El nombre en clave es obligatorio.");
        this.denominacionComercial = requerido(denominacionComercial, "La denominación comercial es obligatoria.");
        this.fechaInicio = requerido(fechaInicio, "La fecha de inicio es obligatoria.");
        this.estado = requerido(estado, "El estado del proyecto es obligatorio.");
        
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de finalización no puede ser anterior a la fecha de inicio.");
        }
        if (estado == EstadoProyecto.FINALIZADO && fechaFin == null) {
            throw new IllegalArgumentException("Un proyecto FINALIZADO debe tener fecha de finalización.");
        }
        if (promotorId == null || promotorId.isBlank()) {
            throw new IllegalArgumentException("El promotor del proyecto es obligatorio.");
        }
        
        this.fechaFin = fechaFin;
        this.promotorId = promotorId.trim();
    }

    private static <T> T requerido(T valor, String mensaje) {
        if (valor == null) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    public ProyectoId getId() { return id; }
    public NombreClave getNombreClave() { return nombreClave; }
    public DenominacionComercial getDenominacionComercial() { return denominacionComercial; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public EstadoProyecto getEstado() { return estado; }
    public String getPromotorId() { return promotorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proyecto)) return false;
        return id.equals(((Proyecto) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Proyecto{codigo=" + id + ", nombreClave=" + nombreClave
                + ", denominacionComercial=" + denominacionComercial
                + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
                + ", estado=" + estado + ", promotor=" + promotorId + "}";
    }
    
}

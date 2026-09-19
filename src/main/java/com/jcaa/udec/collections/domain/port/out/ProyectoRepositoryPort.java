/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author ASUS
 */
public interface ProyectoRepositoryPort {
    void guardar(Proyecto proyecto);
    Optional<Proyecto> buscarPorId(ProyectoId id);
    List<Proyecto> listarTodos();
    boolean eliminarPorId(ProyectoId id);
    boolean existePorId(ProyectoId id);    
}

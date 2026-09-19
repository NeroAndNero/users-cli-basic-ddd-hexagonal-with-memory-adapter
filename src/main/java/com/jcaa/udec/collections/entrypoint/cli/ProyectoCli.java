/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.entrypoint.controller.ProyectoController;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class ProyectoCli {
    private final ProyectoController controller;
    private final Scanner scanner;

    public ProyectoCli(ProyectoController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void iniciar() {
        boolean volver = false;
        while (!volver) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            try {
                switch (opcion) {
                    case "1" -> crear();
                    case "2" -> buscar();
                    case "3" -> actualizar();
                    case "4" -> eliminar();
                    case "5" -> listar();
                    case "0" -> volver = true;
                    default -> System.out.println("Opción no válida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("=== GESTIÓN DE PROYECTOS ===");
        System.out.println("1. Crear proyecto");
        System.out.println("2. Buscar proyecto por código");
        System.out.println("3. Actualizar proyecto");
        System.out.println("4. Eliminar proyecto");
        System.out.println("5. Listar proyectos");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    private void crear() {
        Proyecto p = controller.crear(
                pedir("Código"), pedir("Nombre en clave"), pedir("Denominación comercial"),
                pedir("Fecha de inicio (AAAA-MM-DD)"),
                pedir("Fecha de fin (AAAA-MM-DD, vacío si no aplica)"),
                pedir("Estado (PLANIFICADO, EN_CURSO, FINALIZADO, CANCELADO)"),
                pedir("Código del promotor"));
        System.out.println("Proyecto creado: " + p);
    }

    private void buscar() {
        System.out.println(controller.buscar(pedir("Código")));
    }

    private void actualizar() {
        Proyecto p = controller.actualizar(
                pedir("Código del proyecto a actualizar"), pedir("Nuevo nombre en clave"),
                pedir("Nueva denominación comercial"), pedir("Nueva fecha de inicio (AAAA-MM-DD)"),
                pedir("Nueva fecha de fin (AAAA-MM-DD, vacío si no aplica)"),
                pedir("Nuevo estado"), pedir("Nuevo código del promotor"));
        System.out.println("Proyecto actualizado: " + p);
    }

    private void eliminar() {
        controller.eliminar(pedir("Código del proyecto a eliminar"));
        System.out.println("Proyecto eliminado.");
    }

    private void listar() {
        List<Proyecto> proyectos = controller.listar();
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
            return;
        }
        proyectos.forEach(System.out::println);
    }

    private String pedir(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return scanner.nextLine();
    }    
}

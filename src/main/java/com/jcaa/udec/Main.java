package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.GuardarUsuarioAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ObtenerUsuariosAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ProyectoMemoryRepository;
import com.jcaa.udec.collections.application.service.ActualizarProyectoService;
import com.jcaa.udec.collections.application.service.AgregarUsuarioService;
import com.jcaa.udec.collections.application.service.BuscarProyectoService;
import com.jcaa.udec.collections.application.service.CrearProyectoService;
import com.jcaa.udec.collections.application.service.EliminarProyectoService;
import com.jcaa.udec.collections.application.service.ListarProyectosService;
import com.jcaa.udec.collections.application.service.ObtenerUsuariosService;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import com.jcaa.udec.collections.entrypoint.cli.GuiCli;
import com.jcaa.udec.collections.entrypoint.cli.ProyectoCli;
import com.jcaa.udec.collections.entrypoint.controller.ProyectoController;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControladorImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- Ensamblaje de Usuario (existente) ---
        GuardarUsuarioPort guardarUsuarioPort = new GuardarUsuarioAdapter();
        ObtenerUsuariosPort obtenerUsuariosPort = new ObtenerUsuariosAdapter();
        AgregarUsuarioUseCase agregarUsuarioUseCase = new AgregarUsuarioService(guardarUsuarioPort);
        ObtenerUsuarioUseCase obtenerUsuarioUseCase = new ObtenerUsuariosService(obtenerUsuariosPort);
        UsuarioControlador usuarioControlador =
                new UsuarioControladorImpl(agregarUsuarioUseCase, obtenerUsuarioUseCase);

        // --- Ensamblaje de Proyecto (nuevo) ---
        ProyectoRepositoryPort proyectoRepo = new ProyectoMemoryRepository();
        ProyectoController proyectoController = new ProyectoController(
                new CrearProyectoService(proyectoRepo),
                new BuscarProyectoService(proyectoRepo),
                new ActualizarProyectoService(proyectoRepo),
                new EliminarProyectoService(proyectoRepo),
                new ListarProyectosService(proyectoRepo));

        // --- Scanner compartido (evita dos Scanner sobre System.in) ---
        Scanner scanner = new Scanner(System.in);
        ProyectoCli proyectoCli = new ProyectoCli(proyectoController, scanner);
        GuiCli guiCli = new GuiCli(usuarioControlador, proyectoCli, scanner);
        guiCli.ejecutarAccion();
    }
}

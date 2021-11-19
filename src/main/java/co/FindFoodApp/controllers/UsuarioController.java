package co.FindFoodApp.controllers;

import co.FindFoodApp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

//    Pendiente implementar los metodos para el objeto:
//    listarUsuarios
//    buscarUsuario
//    inactivarUsuario
//    crearUsuario
//    login

}

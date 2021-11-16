package com.example.findfoot.controllers;

import java.util.*;

import com.example.findfoot.models.UsuarioModel;
import com.example.findfoot.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ArrayList<UsuarioModel> ObtenerUsuarios(){
        return UsuarioService.getUsuarios();
        
    }

    @PostMapping
    public UsuarioModel guardarUsuarios(@RequestBody UsuarioModel usuario){
        return UsuarioService.guardarUsuario(usuario);
        
    }

}

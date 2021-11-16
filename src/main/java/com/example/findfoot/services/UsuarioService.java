package com.example.findfoot.services;

import java.util.ArrayList;

import com.example.findfoot.models.UsuarioModel;
import com.example.findfoot.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepositoy;

    public static ArrayList<UsuarioModel> getUsuarios() {
        return (ArrayList<UsuarioModel>) UsuarioRepositoy.findAll();
    }

    public static UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return UsuarioRepositoy.save(usuario);
    }

}

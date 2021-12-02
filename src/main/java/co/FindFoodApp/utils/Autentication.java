package co.FindFoodApp.utils;

import co.FindFoodApp.dto.UsuarioDTO;
import co.FindFoodApp.models.UsuarioModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Autentication {

    public UsuarioDTO autenticacion(UsuarioModel usuarioModel){

        String hash="";
        long tiempo = System.currentTimeMillis();
        if(usuarioModel.getId()!=""){
            hash= Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                    .setSubject(usuarioModel.getNombre())
                    .setIssuedAt(new Date(tiempo))
                    .setExpiration(new Date(tiempo+9000000))
                    .claim("username", usuarioModel.getUsername())
                    .claim("rol", usuarioModel.getRol())
                    .compact();
        }
        return new UsuarioDTO().getUsuarioLogin(usuarioModel,hash);
    }

}

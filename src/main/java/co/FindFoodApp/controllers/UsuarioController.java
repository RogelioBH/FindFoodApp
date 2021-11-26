package co.FindFoodApp.controllers;

import co.FindFoodApp.exceptions.CustomException;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.services.UsuarioService;
import co.FindFoodApp.utils.Autorizacion;
import co.FindFoodApp.utils.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, List<UsuarioModel>>> listar(Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, List<UsuarioModel>> respuesta = new HashMap<>();
        respuesta.put("usuarios", this.usuarioService.listar());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
        UsuarioModel usuarioSave = this.usuarioService.buscarPorNombreUsuario(usuario.getUsername());

        if (usuarioSave.getId() == null) {
            this.usuarioService.crear(usuarioSave);
            respuesta.put("mensaje", "Se resgistro el usuario "+usuarioSave.getUsername()+" correctamente");
        } else {
            respuesta.put("mensaje", "El usuario "+usuarioSave.getUsername()+" ya se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/usuario")
    public ResponseEntity<Map<String, String>> actualizar(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        if(usuario.getPassword() != null){
            usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
        }
        UsuarioModel usuarioSave = this.usuarioService.buscarPorNombreUsuario(usuario.getUsername());

        if (usuarioSave.getId() != null) {
            this.usuarioService.actualizar(usuarioSave);
            respuesta.put("mensaje", "Se actualizo el usuario "+usuarioSave.getUsername()+" correctamente");
        } else {
            respuesta.put("mensaje", "El usuario "+usuarioSave.getUsername()+" no se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/usuario")
    public ResponseEntity<Map<String, String>> borrar(@Valid @RequestBody UsuarioModel usuario, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        UsuarioModel usuarioSave = this.usuarioService.buscarPorNombreUsuario(usuario.getUsername());

        if (usuarioSave.getId() != null) {
            this.usuarioService.borrar(usuarioSave);
            respuesta.put("mensaje", "Se elimino el usuario correctamente");
        } else {
            respuesta.put("mensaje", "El usuario "+usuarioSave.getUsername()+" no se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/usuarios/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody UsuarioModel usuario){
        UsuarioModel usuarioModel=this.usuarioService.buscarPorNombreUsuario(usuario.getUsername());
        if(usuarioModel.getUsername()==null){
            throw new CustomException("Usuario o contraseña incorrectos");
        }

        if(!BCrypt.checkpw(usuario.getPassword(), usuarioModel.getPassword())){
            throw new CustomException("Usuario o contraseña incorrectos");
        }

        String hash="";
        long tiempo = System.currentTimeMillis();
        if(usuarioModel.getId()!=""){
            hash= Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                    .setSubject(usuarioModel.getNombre())
                    .setIssuedAt(new Date(tiempo))
                    .setExpiration(new Date(tiempo+9000000))
                    .claim("username", usuarioModel.getUsername())
                    .claim("correo", usuarioModel.getCorreo())
                    .compact();
        }

        usuarioModel.setHash(hash);
        return ResponseEntity.ok(usuarioModel);

    }

    private void throwError(Errors error) {
        String message = "";
        int index = 0;
        for (ObjectError e : error.getAllErrors()) {
            if (index > 0) {
                message += " | ";
            }
            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(), e.getDefaultMessage());
        }
        throw new CustomException(message);
    }
}

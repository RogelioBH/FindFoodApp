package co.FindFoodApp.controllers;

import co.FindFoodApp.exceptions.CustomException;
import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.DonanteModel;
import co.FindFoodApp.services.DonanteService;
import co.FindFoodApp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class DonanteController {

    @Autowired
    DonanteService donanteService;

    @GetMapping("/donantes")
    public ResponseEntity<Map<String, List<DonanteModel>>> listar(Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, List<DonanteModel>> respuesta = new HashMap<>();
        respuesta.put("donantes", this.donanteService.listar());
        return ResponseEntity.ok(respuesta);
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

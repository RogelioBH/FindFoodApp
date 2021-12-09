package co.FindFoodApp.controllers;

import co.FindFoodApp.exceptions.CustomException;
import co.FindFoodApp.models.BeneficiarioModel;
import co.FindFoodApp.services.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/app")
public class BeneficiarioController {

    @Autowired
    BeneficiarioService beneficiarioService;

    @GetMapping("/beneficiarios")
    public ResponseEntity<List<BeneficiarioModel>> listar() {
        return ResponseEntity.ok(this.beneficiarioService.listar());
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

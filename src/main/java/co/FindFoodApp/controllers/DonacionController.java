package co.FindFoodApp.controllers;

import co.FindFoodApp.exceptions.CustomException;
import co.FindFoodApp.models.DonacionModel;
import co.FindFoodApp.models.UsuarioModel;
import co.FindFoodApp.services.BeneficiarioService;
import co.FindFoodApp.services.DonacionService;
import co.FindFoodApp.services.DonanteService;
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
public class DonacionController {

    @Autowired
    DonacionService donacionService;

    @Autowired
    DonanteService donanteService;

    @Autowired
    BeneficiarioService beneficiarioService;


    @GetMapping("/donaciones")
    public ResponseEntity<Map<String, List<DonacionModel>>> listar(Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, List<DonacionModel>> respuesta = new HashMap<>();
        respuesta.put("donaciones", this.donacionService.listar());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/donacion")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();

        DonacionModel donacionSave = this.donacionService.buscar(donacion);

        if (donacionSave.getId() == null) {
            this.donacionService.crear(donacionSave);
            respuesta.put("mensaje", "Se resgistro la donacion No. "+donacionSave.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "La donacion No. "+donacionSave.getId()+" ya se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/donacion")
    public ResponseEntity<Map<String, String>> actualizar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        DonacionModel donacionSave = this.donacionService.buscar(donacion);

        if (donacionSave.getId() != null) {
            this.donacionService.actualizar(donacionSave);
            respuesta.put("mensaje", "Se actualizo la donacion No. "+donacionSave.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "El usuario "+donacionSave.getId()+" no se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/donacion")
    public ResponseEntity<Map<String, String>> borrar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        DonacionModel donacionSave = this.donacionService.buscar(donacion);

        if (donacionSave.getId() != null) {
            this.donacionService.borrar(donacionSave);
            respuesta.put("mensaje", "Se elimino la donacion No. "+donacionSave.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "El usuario "+donacionSave.getId()+" no se encuentra registrado");
        }
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

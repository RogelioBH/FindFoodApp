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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/app")
public class DonacionController {

    @Autowired
    DonacionService donacionService;

    @GetMapping("/donaciones")
    public ResponseEntity<List<DonacionModel>> listar() {
        return ResponseEntity.ok(this.donacionService.listar());
    }

    @PostMapping("/donacion")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        if (donacion.getId() == null) {
            this.donacionService.crear(donacion);
            respuesta.put("mensaje", "Se resgistro la donacion No. "+donacion.getId()+" correctamente");
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
            this.donacionService.actualizar(donacion);
            respuesta.put("mensaje", "Se actualizo la donacion No. "+donacion.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "La donacion No. "+donacion.getId()+" no se encuentra registrado");
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
            respuesta.put("mensaje", "La donaci??n No. "+donacionSave.getId()+" no se encuentra registrado");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/donacion/select")
    public ResponseEntity<Map<String, String>> seleccionar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        DonacionModel donacionSave = this.donacionService.buscar(donacion);

        if (donacionSave.getId() != null) {
            this.donacionService.seleccionarDonacion(donacion);
            respuesta.put("mensaje", "Se asigno la donacion No. "+donacionSave.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "La Donaci??n No. "+donacionSave.getId()+" no existe");
        }
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/donacion/finish")
    public ResponseEntity<Map<String, String>> finalizar(@Valid @RequestBody DonacionModel donacion, Errors error) {
        if (error.hasErrors()) {
            throwError(error);
        }
        Map<String, String> respuesta = new HashMap<>();
        DonacionModel donacionSave = this.donacionService.buscar(donacion);

        if (donacionSave.getId() != null) {
            this.donacionService.finalizarDonacion(donacion);
            respuesta.put("mensaje", "Se finalizo la donacion No. "+donacionSave.getId()+" correctamente");
        } else {
            respuesta.put("mensaje", "La Donaci??n No. "+donacionSave.getId()+" no existe");
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(value = "/donacion",params = "estado")
    public ResponseEntity<Map<String, List<DonacionModel>>> buscarEstado(@RequestParam(defaultValue = "Disponible", required = true) String estado) {
        Map<String, List<DonacionModel>> respuesta = new HashMap<>();
        respuesta.put("donaciones", this.donacionService.listarEstado(estado));
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

package co.FindFoodApp.controllers;

import co.FindFoodApp.services.DonanteService;
import co.FindFoodApp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class DonanteController {

    @Autowired
    DonanteService donanteService;

//    Pendiente implementar los metodos para el objeto:


}

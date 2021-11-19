package co.FindFoodApp.controllers;

import co.FindFoodApp.services.BeneficiarioService;
import co.FindFoodApp.services.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class BeneficiarioController {

    @Autowired
    BeneficiarioService beneficiarioService;

//    Pendiente implementar los metodos para el objeto:


}

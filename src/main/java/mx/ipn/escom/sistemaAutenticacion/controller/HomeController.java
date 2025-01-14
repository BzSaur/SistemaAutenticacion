package mx.ipn.escom.sistemaAutenticacion.controller;

import org.springframework.stereotype.Controller;
<<<<<<< Updated upstream

@Controller
public class HomeController {
/* 
    @GetMapping("/home")
    public String home() {
        return "home";  // Nombre de la vista para la página de inicio
    }*/
}
=======
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // Asegúrate de que exista un archivo `home.html` en `src/main/resources/templates`
    }
}

>>>>>>> Stashed changes

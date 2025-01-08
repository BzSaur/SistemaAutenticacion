package mx.ipn.escom.sistemaAutenticacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/profile")
    public String profile() {
        return "profile"; // Nombre del archivo HTML sin la extensión .html
    }
}

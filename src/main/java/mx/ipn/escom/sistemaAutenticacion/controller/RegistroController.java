package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Usuario usuario, Model model) {
        try {
            usuarioService.save(usuario); // Asegúrate de que el servicio maneje la lógica de guardado

            model.addAttribute("usuario", usuario);

            return "registroExitoso"; // Redirige a la vista de registro exitoso
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al registrar el usuario.");
            return "register";
        }
    }
}

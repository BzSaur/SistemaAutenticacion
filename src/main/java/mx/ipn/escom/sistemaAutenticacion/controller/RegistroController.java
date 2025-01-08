package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
public String registerUser(Usuario usuario, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes) {
    try {
        usuarioService.saveUser(usuario, imagen);
        redirectAttributes.addFlashAttribute("success", "Registro exitoso.");
        return "redirect:/registroExitoso";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Hubo un error al registrar el usuario: " + e.getMessage());
        return "redirect:/register";
    }
}
}

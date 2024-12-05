package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    // Acceso al dashboard de admin, debe ser solo para usuarios con rol ADMIN
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";  // Asegúrate de tener la vista admin.jsp o admin.html configurada
    }

    // Método para buscar usuario por nombre
    @RequestMapping("/search")
    public String search(String username, Model model) {
        Usuario usuario = usuarioService.searchUserByUsername(username); // Correct method call
        model.addAttribute("usuario", usuario);  // Singular as we are searching for one user
        return "searchResult";  // Asegúrate de tener la vista correspondiente
    }
}

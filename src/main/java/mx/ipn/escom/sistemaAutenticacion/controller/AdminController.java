package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.RequestMapping;
import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> Stashed changes

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    // Acceso al dashboard de admin, debe ser solo para usuarios con rol ADMIN
    @GetMapping("/admin")
<<<<<<< Updated upstream
    public String adminPage() {
        return "admin";  // Asegúrate de tener la vista admin.jsp o admin.html configurada
    }

    // Método para buscar usuario por nombre
    @RequestMapping("/search")
    public String search(String username, Model model) {
        Usuario usuario = usuarioService.searchUserByUsername(username); // Correct method call
        model.addAttribute("usuario", usuario);  // Singular as we are searching for one user
        return "searchResult";  // Asegúrate de tener la vista correspondiente
=======
    public String adminDashboard(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "adminDashboard"; // Vista del panel de administrador
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deleteUserById(userId);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/admin";
>>>>>>> Stashed changes
    }
}

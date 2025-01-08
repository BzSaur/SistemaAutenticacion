package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;  // Asegúrate que UsuarioService esté en el mismo paquete o esté importado

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll()); // Verifica que el método findAll() exista y funcione correctamente.
        return "admin"; // Asegúrate de que existe una vista llamada "admin.html" en el directorio de recursos bajo `/src/main/resources/templates/`.
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.deleteUser(userId);  // Esta función debe existir en UsuarioService
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/updateImage")
    public String updateImage(@RequestParam Long userId, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.updateImage(userId, file);  // Esta función debe existir en UsuarioService
            redirectAttributes.addFlashAttribute("success", "Imagen actualizada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la imagen: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/updateUserInfo")
public String updateUserInfo(@RequestParam Long userId, @RequestParam String nombre, @RequestParam String email, RedirectAttributes redirectAttributes) {
    try {
        Usuario usuario = usuarioService.getUserById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuarioService.saveUser(usuario, null);
        redirectAttributes.addFlashAttribute("success", "Información del usuario actualizada correctamente.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al actualizar la información del usuario: " + e.getMessage());
    }
    return "redirect:/admin";
}
}

package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< Updated upstream
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> Stashed changes

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
<<<<<<< Updated upstream
    public String registerUser(Usuario usuario, 
                               @RequestParam("imagen") MultipartFile imagen,
                               Model model) {
        try {
            // Lógica de registro (guardar usuario, convertir imagen, etc.)
            usuarioService.save(usuario, imagen);  // Asegúrate de que el servicio maneje la lógica de guardado

            // Añadir datos para mostrar en la vista de éxito
            model.addAttribute("usuario", usuario);
            model.addAttribute("imagenBase64", "");  // Aquí puedes convertir la imagen en base64 y añadirla si es necesario

            return "registroExitoso"; // Redirige a la vista de registro exitoso
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al registrar el usuario.");
            return "register";
=======
    public String registerUser(Usuario usuario, @RequestParam(value = "imagen", required = false) MultipartFile imagen, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.saveUser(usuario, imagen);
            redirectAttributes.addFlashAttribute("success", "Registro exitoso.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al registrar el usuario: " + e.getMessage());
            return "redirect:/register";
>>>>>>> Stashed changes
        }
    }
}

package mx.ipn.escom.sistemaAutenticacion.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/usuario/uploadImagen")
    public String uploadImagen(@RequestParam("file") MultipartFile file, @RequestParam("usuarioId") Long usuarioId) {
    try {
        Usuario usuario = usuarioService.findById(usuarioId);
        usuario.setImagen(file.getBytes());
        usuarioService.save(usuario);
        return "redirect:/profile"; // Redirige al perfil del usuario después de cargar la imagen
    } catch (IOException e) {
        e.printStackTrace();
        return "error";
    }
    }


    @PostMapping("/user/register")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }
}

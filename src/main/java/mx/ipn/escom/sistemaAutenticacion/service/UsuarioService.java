package mx.ipn.escom.sistemaAutenticacion.service;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar el PasswordEncoder

    // Guardar un usuario sin imagen
    public void save(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword()); // Codificar la contraseña antes de guardar
        usuario.setPassword(encodedPassword);
        usuarioRepository.save(usuario);
    }

    // Sobrecargar el método para incluir la opción de imagen
    public void save(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
        }
        String encodedPassword = passwordEncoder.encode(usuario.getPassword()); // Asegurar que la contraseña se codifica
        usuario.setPassword(encodedPassword);
        usuarioRepository.save(usuario);
    }

    // Actualizar el perfil de un usuario
    public void updateUserProfile(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
        }
        // Si se actualiza la contraseña, asegúrate de codificarla
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encodedPassword);
        }
        usuarioRepository.save(usuario);
    }

    // Buscar usuario por nombre de usuario
    public Usuario searchUserByUsername(String username) {
        return usuarioRepository.findByNombre(username);
    }

    // Buscar un usuario por ID
    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }
}

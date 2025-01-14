package mx.ipn.escom.sistemaAutenticacion.service;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

<<<<<<< Updated upstream
    // Guardar un usuario
    public void save(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
        }
        usuarioRepository.save(usuario);
    }

    // Actualizar el perfil de un usuario (por ejemplo, cambiar la imagen o datos)
    public void updateUserProfile(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
=======
    public void saveUser(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
        }
        if (usuario.getEnabled() == null) {
            usuario.setEnabled(true);
>>>>>>> Stashed changes
        }
        usuarioRepository.save(usuario);
    }

<<<<<<< Updated upstream
    // Buscar usuario por nombre (nombre de usuario)
    public Usuario searchUserByUsername(String username) {
        return usuarioRepository.findByNombre(username); // Usa el método findByNombre
    }

    // Buscar un usuario por ID
    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
=======
    public void deleteUserById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
>>>>>>> Stashed changes
    }
}

package mx.ipn.escom.sistemaAutenticacion.service;

import mx.ipn.escom.sistemaAutenticacion.entity.Favorito;
import mx.ipn.escom.sistemaAutenticacion.entity.Historial;
import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import mx.ipn.escom.sistemaAutenticacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import mx.ipn.escom.sistemaAutenticacion.repository.FavoritoRepository;
import mx.ipn.escom.sistemaAutenticacion.repository.HistorialRepository;
import java.util.List;  // Importar List


import java.io.IOException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método general para guardar un usuario con o sin imagen
    public void saveUser(Usuario usuario, MultipartFile imagen) throws IOException {
        if (imagen != null && !imagen.isEmpty()) {
            usuario.setImagen(imagen.getBytes());
        }
        usuarioRepository.save(usuario);
    }

    public void deleteUser(Long userId) {
        usuarioRepository.deleteById(userId);
    }

    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void updateImage(Long userId, MultipartFile file) throws IOException {
        Usuario usuario = getUserById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (file != null && !file.isEmpty()) {
            usuario.setImagen(file.getBytes());
        }
        usuarioRepository.save(usuario);
    }

     @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private HistorialRepository historialRepository;

    public Favorito saveFavorito(Favorito favorito) {
        // Guardar el objeto favorito en la base de datos
        return favoritoRepository.save(favorito);
    }

    public List<Historial> getHistorialByUsuario(Long usuarioId) {
        // Obtener el historial de búsquedas de un usuario específico
        return historialRepository.findByUsuarioId(usuarioId);
    }
}


package mx.ipn.escom.sistemaAutenticacion.repository;

import mx.ipn.escom.sistemaAutenticacion.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Use 'nombre' instead of 'username' for searching
    Usuario findByNombre(String nombre);
}

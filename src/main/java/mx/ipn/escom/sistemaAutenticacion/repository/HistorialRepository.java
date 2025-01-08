package mx.ipn.escom.sistemaAutenticacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.ipn.escom.sistemaAutenticacion.entity.Historial;

import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    List<Historial> findByUsuarioId(Long usuarioId);
}

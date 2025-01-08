package mx.ipn.escom.sistemaAutenticacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.ipn.escom.sistemaAutenticacion.entity.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
}

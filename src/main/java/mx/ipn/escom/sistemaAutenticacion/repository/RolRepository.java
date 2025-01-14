package mx.ipn.escom.sistemaAutenticacion.repository;

import mx.ipn.escom.sistemaAutenticacion.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}

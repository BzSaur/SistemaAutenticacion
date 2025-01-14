package mx.ipn.escom.sistemaAutenticacion.service;

import mx.ipn.escom.sistemaAutenticacion.entity.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    public List<Libro> buscarLibros(String searchQuery) {
        // Simulación de resultados de libros. Reemplaza con lógica de búsqueda real.
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("El Quijote", "Miguel de Cervantes"));
        libros.add(new Libro("Cien Años de Soledad", "Gabriel García Márquez"));
        return libros;
    }
}

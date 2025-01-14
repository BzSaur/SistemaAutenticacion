package mx.ipn.escom.sistemaAutenticacion.service;

import org.springframework.stereotype.Service;


@Service
public class BookService {

    public boolean addFavorite(String username, String bookTitle) {
        System.out.printf("Usuario '%s' añadió el libro '%s' a favoritos.%n", username, bookTitle);
        return true;
    }
}

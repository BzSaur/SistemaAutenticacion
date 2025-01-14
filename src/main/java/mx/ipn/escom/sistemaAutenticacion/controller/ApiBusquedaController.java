package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.service.BookService;
import mx.ipn.escom.sistemaAutenticacion.service.AuthorService; // Asegúrate de que el nombre del paquete sea correcto

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ApiBusquedaController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/searchBooks")
    public String searchBooks(@RequestParam("searchQuery") String searchQuery, Model model) {
        try {
            List<Map<String, String>> books = bookService.searchBooks(searchQuery);
            model.addAttribute("books", books);
            return "books"; // books.html se usará para mostrar los resultados.
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al buscar libros: " + e.getMessage());
            return "home";
        }
    }

    @PostMapping("/searchAuthors")
    public String searchAuthors(@RequestParam("authorName") String authorName, Model model) {
        try {
            List<Map<String, String>> authors = authorService.searchAuthors(authorName);
            model.addAttribute("authors", authors);
            return "authors"; // authors.html se usará para mostrar los resultados.
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al buscar autores: " + e.getMessage());
            return "home";
        }
    }
}
package mx.ipn.escom.sistemaAutenticacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class UsuarioController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String homePage() {
        return "home";  // Página principal
    }

    // Método para buscar libros por título
    @RequestMapping("/searchBooks")
    public String searchBooks(String searchQuery, Model model) {
        String url = "https://openlibrary.org/search.json";
        // Realiza la solicitud HTTP GET a la API de OpenLibrary
        String response = restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("title", searchQuery)
                .toUriString(), String.class);

        model.addAttribute("books", response);  // Pasamos los resultados a la vista
        return "home";  // Vista que mostrará los libros encontrados
    }

    // Método para buscar autores por nombre
    @RequestMapping("/searchAuthors")
    public String searchAuthors(String authorName, Model model) {
        String url = "https://openlibrary.org/search.json";
        // Realiza la solicitud HTTP GET a la API de OpenLibrary
        String response = restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("author", authorName)
                .toUriString(), String.class);

        model.addAttribute("authors", response);  // Pasamos los resultados a la vista
        return "home";  // Vista que mostrará los autores encontrados
    }
}

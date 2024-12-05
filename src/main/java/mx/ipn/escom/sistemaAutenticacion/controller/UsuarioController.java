package mx.ipn.escom.sistemaAutenticacion.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UsuarioController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String homePage() {
        System.out.println("Accediendo a la página home.");
        return "home";  // Asegúrate de que el archivo se llama home.html y está en /src/main/resources/templates
    }

    // Método para buscar libros por título
    @PostMapping("/searchBooks")
    public String searchBooks(String searchQuery, Model model) {
        String url = "https://openlibrary.org/search.json";
        String response = restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("title", searchQuery)
                .toUriString(), String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            JsonNode docs = root.path("docs");
            model.addAttribute("books", docs);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error procesando los datos de los libros");
        }
        
        return "books";  // Vista que mostrará los libros encontrados
    }

    // Método para buscar autores por nombre
    @PostMapping("/searchAuthors")
    public String searchAuthors(String authorName, Model model) {
        String url = "https://openlibrary.org/search.json";
        String jsonResponse = restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("author", authorName)
                .toUriString(), String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> authors = new HashSet<>();  // Usamos un Set para evitar duplicados
        
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode docs = rootNode.path("docs");
            
            // Iteramos sobre los documentos de la búsqueda
            if (docs.isArray()) {
                for (JsonNode doc : docs) {
                    JsonNode authorNames = doc.path("author_name");
                    if (authorNames.isArray()) {
                        for (JsonNode name : authorNames) {
                            String author = name.asText().trim();
                            // Añadimos el nombre del autor al Set (sin duplicados)
                            if (author.toLowerCase().contains(authorName.toLowerCase())) { // Filtro por coincidencia de nombre
                                authors.add(author);
                            }
                        }
                    }
                }
            }
            
            model.addAttribute("authors", authors);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error procesando los datos de los autores");
        }
        
        return "authors";  // Vista que mostrará los autores encontrados
    }
}

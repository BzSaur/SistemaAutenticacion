package mx.ipn.escom.sistemaAutenticacion.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final int MAX_RESULTS = 10;

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @PostMapping("/searchBooks")
    public String searchBooks(String searchQuery, Model model) {
        try {
            String url = "https://openlibrary.org/search.json";
            String response = restTemplate.getForObject(
                UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("title", searchQuery)
                    .queryParam("limit", MAX_RESULTS)
                    .toUriString(), 
                String.class
            );
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode docs = root.path("docs");
            
            List<Map<String, String>> books = new ArrayList<>();
            
            for (JsonNode doc : docs) {
                Map<String, String> book = new HashMap<>();
                book.put("title", doc.path("title").asText("Sin título"));
                
                // Get author names
                JsonNode authors = doc.path("author_name");
                if (authors.isArray() && authors.size() > 0) {
                    book.put("author", authors.get(0).asText("Autor desconocido"));
                } else {
                    book.put("author", "Autor desconocido");
                }
                
                // Get publication year
                JsonNode year = doc.path("first_publish_year");
                if (!year.isMissingNode()) {
                    book.put("year", year.asText("Año desconocido"));
                }
                
                // Get ISBN
                JsonNode isbns = doc.path("isbn");
                if (isbns.isArray() && isbns.size() > 0) {
                    book.put("isbn", isbns.get(0).asText("ISBN no disponible"));
                }
                
                books.add(book);
            }
            
            model.addAttribute("books", books);
            model.addAttribute("searchQuery", searchQuery);
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al procesar la búsqueda de libros");
        }
        
        return "books";
    }

    @PostMapping("/searchAuthors")
    public String searchAuthors(String authorName, Model model) {
        try {
            String url = "https://openlibrary.org/search/authors.json";
            String response = restTemplate.getForObject(
                UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("q", authorName)
                    .toUriString(), 
                String.class
            );
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode docs = root.path("docs");
            
            List<Map<String, String>> authors = new ArrayList<>();
            
            for (JsonNode doc : docs) {
                if (authors.size() >= MAX_RESULTS) break;
                
                Map<String, String> author = new HashMap<>();
                author.put("name", doc.path("name").asText("Nombre desconocido"));
                
                // Get birth date
                JsonNode birth = doc.path("birth_date");
                if (!birth.isMissingNode()) {
                    author.put("birth_date", birth.asText("Fecha de nacimiento desconocida"));
                }
                
                // Get top work
                JsonNode topWork = doc.path("top_work");
                if (!topWork.isMissingNode()) {
                    author.put("top_work", topWork.asText("Obra más conocida no disponible"));
                }
                
                // Get work count
                JsonNode workCount = doc.path("work_count");
                if (!workCount.isMissingNode()) {
                    author.put("work_count", "Obras publicadas: " + workCount.asText("0"));
                }
                
                authors.add(author);
            }
            
            model.addAttribute("authors", authors);
            model.addAttribute("searchQuery", authorName);
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al procesar la búsqueda de autores");
        }
        
        return "authors";
    }
}
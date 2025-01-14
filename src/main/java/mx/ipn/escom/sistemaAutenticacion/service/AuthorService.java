package mx.ipn.escom.sistemaAutenticacion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    private final RestTemplate restTemplate = new RestTemplate();

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> searchAuthors(String query) {
        String url = "https://openlibrary.org/search/authors.json?q=" + query;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, String>> authors = new ArrayList<>();
        if (response != null && response.containsKey("docs")) {
            List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
            for (Map<String, Object> doc : docs) {
                Map<String, String> author = new HashMap<>();
                author.put("name", (String) doc.getOrDefault("name", "Desconocido"));
                author.put("birthYear", doc.containsKey("birth_date") ? doc.get("birth_date").toString() : "Desconocido");
                authors.add(author);
            }
        }
        return authors;
    }
}

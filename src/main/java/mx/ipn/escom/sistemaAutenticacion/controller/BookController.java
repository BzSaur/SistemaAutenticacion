package mx.ipn.escom.sistemaAutenticacion.controller;

import mx.ipn.escom.sistemaAutenticacion.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books/addFavorite")
    public String addFavorite(@RequestParam String bookTitle, @RequestParam String username, RedirectAttributes redirectAttributes) {
        boolean added = bookService.addFavorite(username, bookTitle);
        if (added) {
            redirectAttributes.addFlashAttribute("success", "Libro añadido a favoritos.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al añadir el libro a favoritos.");
        }
        return "redirect:/home";
    }
    
}

package mx.ipn.escom.sistemaAutenticacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mx.ipn.escom.sistemaAutenticacion.entity.Favorito;
import mx.ipn.escom.sistemaAutenticacion.entity.Historial;
import mx.ipn.escom.sistemaAutenticacion.service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/agregarFavorito")
    public Favorito agregarFavorito(@RequestBody Favorito favorito) {
        return usuarioService.saveFavorito(favorito);
    }

    @GetMapping("/historial/{usuarioId}")
    public List<Historial> obtenerHistorial(@PathVariable Long usuarioId) {
        return usuarioService.getHistorialByUsuario(usuarioId);
    }
}

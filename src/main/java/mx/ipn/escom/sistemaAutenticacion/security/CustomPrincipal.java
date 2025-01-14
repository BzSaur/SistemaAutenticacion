package mx.ipn.escom.sistemaAutenticacion.security;

import java.security.Principal;

public class CustomPrincipal implements Principal {

    private String nombre;

    public CustomPrincipal(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getName() {
        return this.nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}

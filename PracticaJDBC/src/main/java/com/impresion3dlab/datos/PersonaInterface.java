package com.impresion3dlab.datos;

import com.impresion3dlab.domain.Persona;
import java.util.List;

interface PersonaInterface {
    List<Persona> listarPersona();
    Persona obtenerPersona(Persona persona);
    int ingresarPersona(Persona persona);
    int actualizarPersona(Persona persona);
    int eliminarPersona(Persona persona);
}

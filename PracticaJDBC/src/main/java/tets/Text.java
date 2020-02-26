/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tets;

import com.impresion3dlab.datos.PersonaDao;
import com.impresion3dlab.domain.Persona;
import java.util.List;

/**
 *
 * @author Alexiscr
 */
public class Text {
    public static void main(String[] args) {
        PersonaDao personadao = new PersonaDao();
        List<Persona> personas = personadao.listarPersona();
        
        for (Persona persona : personas) {
            System.out.println(persona);
        }
        Persona obtenerPersona = personadao.obtenerPersona(new Persona(1));
        System.out.println(obtenerPersona);
        
        obtenerPersona.setNombre("Alexis");
        
        personadao.actualizarPersona(obtenerPersona);
        
       // personadao.ingresarPersona(new Persona("Lola", "Traeilera", 15, "4@mail.com"));
       personadao.eliminarPersona(new Persona(6));
        
        
        
    }
    
    
}

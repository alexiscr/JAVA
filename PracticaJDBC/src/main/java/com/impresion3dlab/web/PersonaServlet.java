package com.impresion3dlab.web;

import com.impresion3dlab.datos.PersonaDao;
import com.impresion3dlab.domain.Persona;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PersonasServlet")
public class PersonaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action != null) {
            switch (action) {
                case "modificar":
                    this.modificarAction(request, response);
                    break;
                case "eliminar":
                    this.eliminarAction(request, response);
                    break;
                default:
                    this.defaultAction(request, response);
                    break;
            }
        } else {
            this.defaultAction(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action != null) {
            switch (action) {
                case "insertar":
                    this.insertarAction(request, response);
                    break;
                case "editar":
                    this.editarAction(request, response);
                    break;
                default:
                    this.defaultAction(request, response);
                    break;
            }
        } else {
            this.defaultAction(request, response);
        }
    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
       PersonaDao personadao = new PersonaDao();
        List<Persona> personas = personadao.listarPersona();
        
        int count = personas.size();
        
        HttpSession session = request.getSession();
        session.setAttribute("personas", personas);
        response.sendRedirect("personas.jsp");
    }

    private void insertarAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String email = request.getParameter("email");

        Persona persona = new Persona(nombre, apellido, edad, email);

        int resultado = new PersonaDao().ingresarPersona(persona);

        this.defaultAction(request, response);
    }

    private void modificarAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        PersonaDao personaDao = new PersonaDao();
        Persona persona = personaDao.obtenerPersona(new Persona(idPersona));
        request.setAttribute("persona", persona);
        request.getRequestDispatcher("editarPersona.jsp").forward(request, response);

    }

    private void editarAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String email = request.getParameter("email");

        Persona persona = new Persona(idPersona, nombre, apellido, edad, email);

        int resultado = new PersonaDao().actualizarPersona(persona);
        
        this.defaultAction(request, response);
    }

    private void eliminarAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));

        Persona persona = new Persona(idPersona);

        int registroEliminado = new PersonaDao().eliminarPersona(persona);

        this.defaultAction(request, response);
    }

}

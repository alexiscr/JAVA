package com.impresion3dlab.datos;

import com.impresion3dlab.domain.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDao implements PersonaInterface{
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, edad, email FROM persona";
    private static final String SQL_SELECT_BY_ID = "SELECT id_persona, nombre, apellido, edad, email FROM persona WHERE id_persona = ? ";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, edad, email ) VALUES(?, ?, ? ,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, edad = ?, email = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

    @Override
    public List<Persona> listarPersona() {
        List<Persona> personas = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEdad(rs.getInt("edad"));
                persona.setEmail(rs.getString("email"));
                
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }
        
        return personas;
    }

    @Override
    public Persona obtenerPersona(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, persona.getIdPersona());
            rs = stmt.executeQuery();
            rs.absolute(1);
            
            persona.setNombre(rs.getString("nombre"));
            persona.setApellido(rs.getString("apellido"));
            persona.setEdad(rs.getInt("edad"));
            persona.setEmail(rs.getString("email"));
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(rs);
            Conexion.close(conn);
        }
        
        return persona;
    }

    @Override
    public int ingresarPersona(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int resultado = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setInt(3, persona.getEdad());
            stmt.setString(4, persona.getEmail());
            resultado = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);            
            Conexion.close(conn);            
        }
        
        return resultado;
    }

    @Override
    public int actualizarPersona(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int resultado = 0;        
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getEdad());
            stmt.setString(4, persona.getEmail());
            stmt.setInt(5, persona.getIdPersona());
            
            resultado = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    @Override
    public int eliminarPersona(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int resultado = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            resultado = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    
}

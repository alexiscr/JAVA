package com.impresion3dlab.datos;



import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ejemplo_persona?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";    
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD ="root";
    private static BasicDataSource dataSource;
    
    public static DataSource getDataSource(){
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(DB_URL);
            dataSource.setUsername(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (Exception e) {
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (Exception e) {
        }                
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
    
   
}

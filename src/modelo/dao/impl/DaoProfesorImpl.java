
package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.dto.Profesor;


public class DaoProfesorImpl {
    
    private final Conexion conexion;
    private String mensaje;

    public DaoProfesorImpl() {
        this.conexion = new Conexion();
    }
 
    
    
    public Profesor validarSesion(String usuario, String password) {
        
        Profesor profesor = null ;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("apellidos,")
                .append("usuario, ")
                .append("password, ")
                .append("dni ")
                .append("FROM profesor WHERE  usuario= ? AND password=?");
                

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, usuario);
            ps.setString(2, password );
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    profesor = new  Profesor();
                    profesor.setId(rs.getInt(1));
                    profesor.setNombre(rs.getString(2));
                    profesor.setApellidos(rs.getString(3));
                    profesor.setUsuario(rs.getString("usuario"));
                    profesor.setPassword(rs.getString("password"));
                    profesor.setDni(rs.getString(6));

                } else {
                    profesor = null;
                    mensaje = "Credenciales inválidas";
                }

            } catch (SQLException e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return profesor;
    }


    public String getMessage() {
        return mensaje;
    }
    
    
    
}

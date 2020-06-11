
package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Conexion;
import modelo.dao.DaoNotas;

public class DaoNotasImpl implements DaoNotas{
    
    private  Conexion conectaDb;
    private String message;

    public DaoNotasImpl() {
        this.conectaDb = new Conexion();
    }

    @Override
    public String insertarNotasFK(String idalumno, String idmateria) {
         
        int ida = Integer.parseInt(idalumno);
        int idm = Integer.parseInt(idmateria);
        
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO notas( ")
                .append("idalumno,")
                .append("idmateria ")
                .append(") VALUES (?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, ida);
            ps.setInt(2, idm);
            

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "cero filas insertadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();// si hay error
        }

        return message;
    }

    @Override
    public void getNotasMateria(int idmateria) {
        
    }
    
}

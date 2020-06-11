package modelo.dao.impl;

import static controlador.ControladorMenu.direccion;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import modelo.Conexion;
import modelo.dao.DaoAlumno;
import modelo.dto.Alumno;
import vista.Registro_Alumno;

public class DaoAlumnoImpl implements DaoAlumno {

    Registro_Alumno vista_alumno;//vista
    Alumno alumno;
    private String message;
    private Conexion conexion;

    public DaoAlumnoImpl() {
        this.conexion = new Conexion();
    }

    //Metodo para guardar un alumno
    @Override
    public String insertarAlumno(Alumno alumno) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO alumno( ")
                .append("nombre,")
                .append("apellidos,")
                .append("dni,")
                .append("genero ")
                //.append("fechaNacimiento, ")  
                .append(") VALUES (?,?,?,?) ");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellidos());
            ps.setString(3, alumno.getDni());
            ps.setString(4, alumno.getGenero());
            //  ps.setString(5, alumno.getFechaNacimiento().toString());
            //  ps.setFloat(4, alumno.getPeso());
            //  ps.setString(5, alumno.getRaza());

            int ctos = ps.executeUpdate();
            
            if (ctos == 0) {
                message = "cero filas insertadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();// si hay error
        }

        return message;
    }

    /*
    
    public void relacionAlumnoMateria(){
        
        //select idmateria from materia where nombremateria=?
        
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO notas( ")
                .append("nombre,")
                .append("apellidos,")
                .append("dni,")
                .append("genero,")
                .append("fechaNacimiento, ")
                .append("idmateria ")
                .append(") VALUES (?,?,?,?,?,(select idmateria from materia where nombremateria=?)) ");
        
    }
     */
    //lista en la tabla 
    @Override
    public List<Alumno> listarAlumnos(String materia) {
        List<Alumno> list = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("dni,")
                .append("fecha_nacimiento,")
                .append("peso,")
                .append("raza ")
                .append("FROM perros");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            list = new ArrayList<>();
            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setId(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setDni(rs.getString(3));
                //   alumno.setFecha_nacimiento(LocalDate.parse(rs.getString(4)));
                //  alumno.setPeso(rs.getFloat(5));
                //  alumno.setRaza(rs.getString(6));

                list.add(alumno);
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    //obtener donde se guardaran los cambios
    public String midirectorio(String docente) {
        File directorio = new File(direccion + docente); // "C:/Users/DAVID/Desktop/materias_david"

        if (directorio.exists()) {//la carpeta del docente ya existe
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;

        } else {
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;
        }
    }
    
    //hacer combobox con materias de docente respectivo
    @Override
    public void comboBoxMaterias(Registro_Alumno vista_alumno, String docente) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String filename = (midirectorio(docente));
        File file = new File(filename);
        try {
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {

                String line;
                line = in.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                String curso;
                curso = st.nextToken();
                model.addElement(curso);
                vista_alumno.comboMateria.setModel(model);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }
    }

    @Override
    public String getIdAlumno(String dni) {
       // Perros perro = new Perros();

       String id=null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id ")
                .append("FROM alumno ")
                .append("WHERE dni = ?");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {         
                   id =  rs.getString("id");    

                } else {
                    id= null;
                }

            } catch (SQLException e) {
                message = e.getMessage();
                System.out.println("Error getIdAlumno" + message);
            }

        } catch (SQLException e) {
            message = e.getMessage();
            System.out.println("Error 1 getIdAlumno" + message);
        }
        
        return id;
    }

    
    @Override
    public boolean existeAlumno(Alumno alumno) {
       //Alumno perro = new Perros();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id ")
                .append("FROM alumno ")
                .append("WHERE dni = ?");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, alumno.getDni());
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return true;

                } else {
                    return false;
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return false;
     
    }

}

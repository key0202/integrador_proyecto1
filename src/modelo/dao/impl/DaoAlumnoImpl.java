package modelo.dao.impl;

import static controlador.ControladorMenu.direccion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
    select * from alumno INNER JOIN notas on (alumno.id = notas.idalumno)
    
    CONSULTA 
    select * from alumno a
INNER JOIN notas n on (a.id = n.idalumno)
INNER JOIN materia m on (n.idmateria= m.id)
WHERE m.nombremateria='arte'
   
     */
    //LISTAR EN LA TABLAR DE LA VISTA REGISTRO_ALUMNO SEGUN MATERIA 
    @Override
    public String listarAlumnos(String materia, JTable tablaAlumnos) {

        //tablaAlumnos.removeAll();
        /* int numDatos = tablaAlumnos.getRowCount();
        for (int i = 0; i < numDatos; i++) {
            tablaAlumnos.removeAll(i);
        }*/
        limpiarTabla(tablaAlumnos);

        List<Alumno> list = null;

        DefaultTableModel model = null;
        String[] columnas = {"ID", "NOMBRE", "APELLIDOS", "DNI"};

        model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("a.id, ")
                .append("a.nombre,")
                .append("a.apellidos,")
                .append("a.dni ")
                .append("FROM alumno a ")
                .append("INNER JOIN notas n on (a.id = n.idalumno) ")
                .append("INNER JOIN materia m on (n.idmateria= m.id) ")
                .append("WHERE m.nombremateria=? ");

        System.out.println("Antes de conexion");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, materia);

            System.out.println("conectando");

            try (ResultSet rs = ps.executeQuery();) {

                list = new ArrayList<>();

                System.out.println("conectando");

                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt("id"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellidos(rs.getString("apellidos"));
                    alumno.setDni(rs.getString("dni"));

                    String[] datos = {String.valueOf(alumno.getId()), alumno.getNombre(), alumno.getApellidos(), alumno.getDni()};

                    // System.out.println(String.valueOf(alumno.getId()) + " " + alumno.getNombre());
                    list.add(alumno);
                    model.addRow(datos);

                    // System.out.println("datos");
                    tablaAlumnos.setModel(model);
                }

            } catch (Exception e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
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

    //donde se guardaran los cambios
    public String directorioDestino(String docente, String materia) {
        File directorio = new File(direccion + docente); // "C:/Users/DAVID/Desktop/TeachTools_david"

        if (directorio.exists()) {//la carpeta del docente ya existe            
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/alumnos_" + materia + ".txt";
            return midirectorio;

        } else {
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/alumnos_" + materia + ".txt";
            return midirectorio;
        }
    }

    //eliminar archivo txt
    public void borrarTxt(String midirectorio) {
        File directoriodelete = new File(midirectorio);
        directoriodelete.delete();
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

        String id = null;
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
                    id = rs.getString("id");

                } else {
                    id = null;
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

    @Override
    public String listarTodosAlumnos(JTable tabla) {

        DefaultTableModel model = null;
        String[] columnas = {"ID", "NOMBRE", "APELLIDOS", "DNI"};

        model = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tabla.setModel(model);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id,")
                .append("nombre,")
                .append("apellidos,")
                .append("dni,")
                .append("genero ")
                .append("FROM alumno");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setDni(rs.getString("dni"));

                String[] datos = {String.valueOf(alumno.getId()), alumno.getNombre(), alumno.getApellidos(), alumno.getDni()};

                model.addRow(datos);

                // System.out.println("datos");
            }

            tabla.setModel(model);
        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    public void limpiarTabla(JTable tabla) {
        String[] columnas = {"ID", "NOMBRE", "APELLIDOS", "DNI"};
        DefaultTableModel model = new DefaultTableModel(null, columnas);

        tabla.setModel(model);
        int numDatos = model.getRowCount();
        while (numDatos > 1) {
            model.removeRow(1);
        }

    }

    //https://www.youtube.com/watch?v=e6NQDcCUAZY
    @Override
    public void exportarAlumnos(Alumno alumno, Registro_Alumno vista_alumno, String docente) {
        borrarTxt(directorioDestino(docente, alumno.getMateria()));
        try {
            FileWriter file = new FileWriter(directorioDestino(docente, alumno.getMateria()), true);
            BufferedWriter bw = new BufferedWriter(file);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("ID\t\tNOMBRE\t\tAPELLIDOS\t\tDNI");
            for (int i = 0; i < vista_alumno.tablaAlumnos.getRowCount(); i++) { //filas
                for (int j = 0; j < vista_alumno.tablaAlumnos.getColumnCount(); j++) {//columnas
                    //bw.write(vista_alumno.tablaAlumnos.getValueAt(i, j).toString() + "\t");
                    //bw.write(vista_alumno.tablaAlumnos.getModel().getValueAt(i, j) + "\t");
                    pw.print(vista_alumno.tablaAlumnos.getModel().getValueAt(i, j) + "\t\t");
                }
                bw.write("\n________________________________________________\n");
                //bw.newLine();
            }
            pw.close();
            //bw.close();
            //file.close();

            //PrintWriter pw = new PrintWriter(bw);
            //pw.println(asistencia.getStringFecha() + "|" + asistencia.getNombre() + " " + asistencia.getApellidos() + "|" + asistencia.getDni() + "|" + asistencia.getTipo());
            //pw.close();
            JOptionPane.showMessageDialog(null, "Registro de alumnos guardado en " + direccion + docente);

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error exportarAlumnos(): " + error);
        }
    }

}

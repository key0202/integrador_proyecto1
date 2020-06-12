package modelo.dao.impl;

import controlador.ControladorMenu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.dao.DaoMateria;
import modelo.dto.Materia;
import vista.Registro_Materia;


public class DaoMateriaImpl implements DaoMateria {

    //private final String username = "MiKeyla";
    //private final String destino = "Desktop"; // Desktop, Documents, etc
    //private final String direccion = "C:/Users/" + username + "/" + destino + "/materias_";
    
    String username = ControladorMenu.username;
    String destino = ControladorMenu.destino;
    String direccion = ControladorMenu.direccion;
    int num = 0;
    private String message;
    private Conexion conexion;

    public DaoMateriaImpl() {
        this.conexion = new Conexion();
    }
    
    
    

    //obtener donde se guardaran los cambios
    public String midirectorio(String docente) {
        File directorio = new File(direccion + docente);

        if (directorio.exists()) {//la carpeta del docente ya existe
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;

        } else {
            directorio.mkdirs();
            String midirectorio = direccion + docente + "/materias.txt";
            return midirectorio;
        }
    }

    public void inicioApp(Registro_Materia vistaMateria, String docente) {
        //vista_materia.labelTitulo.setText("Bienvenido(a) " + docente);
        vistaMateria.setResizable(false);
        vistaMateria.setTitle("registro de materias " + docente);        
        vistaMateria.setLocationRelativeTo(null);
        vistaMateria.jTextArea1.setEditable(false);
    }

    @Override
    public void leer(Registro_Materia vistaMateria, String docente) {
        inicioApp(vistaMateria, docente);

        String linea, materia;
        vistaMateria.jTextArea1.setText("");
        //Insertando encabezado al textarea
        vistaMateria.jTextArea1.append("");
        vistaMateria.jTextArea1.append(" N°\tMATERIA \n");
        vistaMateria.jTextArea1.append("-----------------------------------\n");

        File archivo = new File(midirectorio(docente));

        if (archivo.exists()) { //el archivo ya existe
            try {
                FileReader file = new FileReader(archivo);
                BufferedReader br = new BufferedReader(file); //ata al FileReader
                linea = br.readLine();

                while (linea != null) {
                    StringTokenizer st = new StringTokenizer(linea, ",");
                    materia = st.nextToken();
                    //incremento en el contador de registros
                    num++;

                    String numero = String.valueOf(num);
                    vistaMateria.jTextArea1.append(numero + "\t" + materia + "\n");

                    linea = br.readLine();
                }
                br.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error leer(): " + e);
            }
        }
    }

    @Override
    public void agregarMateria(Materia materia, Registro_Materia vistaMateria, String docente) {
        try {
            FileWriter file = new FileWriter(midirectorio(docente), true);
            BufferedWriter bw = new BufferedWriter(file);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(materia.getNombreMateria() + ",");
            pw.close();

            num++;

            String numero = String.valueOf(num);
            vistaMateria.jTextArea1.append(numero + "\t" + materia.getNombreMateria() + "\n");
            //limpiar los campos
            vistaMateria.jTextField1.setText("");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error agregarMateria(): " + error);
        }
    }

    @Override
    public String getIdMateria(String nombreMateria) {
  

       String id=null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id ")
                .append("FROM materia ")
                .append("WHERE nombremateria = ?");
        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, nombreMateria);
            
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {         
                   id =  rs.getString("id");

                } else {
                    id= null;
                }

            } catch (SQLException e) {
                message = e.getMessage();
                System.out.println("Error getIdMateria" + message);
            }

        } catch (SQLException e) {
            message = e.getMessage();
            System.out.println("Error 1 getIdMateria" + message);
        }
        
        return id;
    }

    @Override
    public String insertarMateria(Materia materia) {
        //To change body of generated methods, choose Tools | Templates.
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO materia( ")
                .append("nombremateria ")
                .append(") VALUES (?) ");

        try (Connection cn = conexion.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setString(1, materia.getNombreMateria());
            
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "cero filas insertadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();// si hay error
        }
        return message;    
    }
    

}

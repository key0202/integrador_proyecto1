package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import modelo.dao.DaoAlumno;
import modelo.dao.DaoMateria;
import modelo.dao.impl.DaoAlumnoImpl;
import modelo.dao.impl.DaoMateriaImpl;
import vista.MenuPrincipal;
import vista.Registro_Alumno;
import vista.Registro_Asistencia;
import vista.Registro_Materia;
import vista.Registro_Nota;


public class ControladorMenu implements ActionListener {

    //jframe de este controlador
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    //jframes a los que se dirigira
    Registro_Materia registroMateria = new Registro_Materia();
    Registro_Alumno registroAlumno = new Registro_Alumno();
    Registro_Asistencia registroAsistencia = new Registro_Asistencia();
    Registro_Nota registroNota = new Registro_Nota();

    //Dao de los jframes
    DaoMateria daoMateria = new DaoMateriaImpl();
    DaoAlumno daoAlumno = new DaoAlumnoImpl();
    

    //obtenemos nombre de usuario del logins
    String docente = Controlador.docente;

    //direccion donde se crearan los archivos txt
    public static String username = "MiKeyla";
    public static String destino = "Desktop"; // Desktop, Documents, etc
    public static String direccion = "C:/Users/" + username + "/" + destino + "/TeachTools_";

    public ControladorMenu(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        eventos();
        datosDocente();
    }
    
    
    //Eventos de los botones del frame Menu Principal
    public void eventos(){
        this.menuPrincipal.btnRegistrarMateria.addActionListener(this);
        this.menuPrincipal.btnRegistrarAlumno.addActionListener(this);
        this.menuPrincipal.btnRegistrarAsistencia.addActionListener(this);
        this.menuPrincipal.btnRegistrarNotas.addActionListener(this);
    }

    //datos del docente
    public void datosDocente() {
        menuPrincipal.txtNombreProfesor.setText(Controlador.docente);
        menuPrincipal.txtNombreProfesor1.setText(Controlador.apellido);
        menuPrincipal.txtdniProfesor.setText(Controlador.DNI);
        menuPrincipal.txtUsuario.setText(Controlador.user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Boton Registrar MATERIA del Menu Principañ
        if (e.getSource() == menuPrincipal.btnRegistrarMateria) {
            //llamamos al controlador del jframe al que nos dirigimos
            ControladorMateria ctrlMateria = new ControladorMateria(registroMateria, daoMateria);
            ctrlMateria.iniciar();
            menuPrincipal.dispose();
        }

        //Boton Registrar ALUMNO del Menu principal
        if (e.getSource() == menuPrincipal.btnRegistrarAlumno) {
            File directorio = new File(direccion + docente);
            if (directorio.exists()) {
                //llamamos al controlador del jframe al que nos dirigimos
                //JOptionPane.showMessageDialog(null, "Registrar alumno");
               
                ControladorAlumno ctrlAlumno = new ControladorAlumno(registroAlumno, daoAlumno);
                
                ctrlAlumno.iniciar();
   
                menuPrincipal.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Primero se debe registrar las materias " + docente);
            }
        }

        //Boton Registrar ASISTENCIA del Menu principal
        if (e.getSource() == menuPrincipal.btnRegistrarAsistencia) {
            File directorio = new File(direccion + docente);
            if (directorio.exists()) {
                //llamamos al controlador del jframe al que nos dirigimos
                JOptionPane.showMessageDialog(null, "Registrar asistencia");
                //menuPrincipal.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Primero se debe registrar las materias " + docente);
            }
        }

        //Boton Registrar NOTAS del Menu principal
        if (e.getSource() == menuPrincipal.btnRegistrarNotas) {
            File directorio = new File(direccion + docente);
            if (directorio.exists()) {
                //llamamos al controlador del jframe al que nos dirigimos
                JOptionPane.showMessageDialog(null, "Registrar nota");
                //menuPrincipal.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Primero se debe registrar las materias " + docente);
            }
        }
    }

}

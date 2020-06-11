package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

import modelo.dao.impl.DaoProfesorImpl;
import modelo.dto.Profesor;

public class Controlador implements ActionListener {

    //jframe de este controlador
    Login login;
    DaoProfesorImpl daoProfesor;

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    //enviar datos entre interfaces
    public static String docente = "";
    public static String apellido = "";
    public static String DNI = "";
    public static String user = "";
    Profesor profesor = null;

    public Controlador(Login login, DaoProfesorImpl daoProfesor, MenuPrincipal menuPrincipal) {
        this.daoProfesor = daoProfesor;
        this.login = login;
        this.login.btnIngresar.addActionListener(this);
        this.menuPrincipal = menuPrincipal;
        iniciarVista();
    }

    private void iniciarVista() {
        login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnIngresar) {
            //Aciones del boton ingresar
            String usuario = login.txtUsuario.getText();
            String password = login.txtPassword.getText();

            try {

                profesor = daoProfesor.validarSesion(usuario, password);
                if (profesor != null) {
                    docente = profesor.getNombre().trim().toLowerCase();
                    apellido = profesor.getApellidos();
                    DNI = profesor.getDni();
                    user = profesor.getUsuario();
                    
                    JOptionPane.showMessageDialog(null, "Bienvenido(a) " + docente);
                    //JOptionPane.showMessageDialog(null, profesor);
                    
                    //llamamos al controlador del jframe al que nos dirigimos
                    ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
                    ctrlMenu.menuPrincipal.setVisible(true);
                    login.dispose();
                    //menuPrincipal.setVisible(true);
                } else {
                    //JOptionPane.showMessageDialog(null, usuario);
                    //JOptionPane.showMessageDialog(null, password);
                    //JOptionPane.showMessageDialog(null, profesor);
                    JOptionPane.showMessageDialog(null, "Credencianels invalidas");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage());
            }

        }

    }

}

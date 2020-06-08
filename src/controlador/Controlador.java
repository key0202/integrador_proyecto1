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

    Login login ;
    DaoProfesorImpl daoProfesor;
    MenuPrincipal menuPrincipal = new MenuPrincipal();
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

            /* profesor= daoProfesor.validarSesion(usuario, password); 
            if( profesor.getUsuario() == null){
                
                menuPrincipal.setVisible(true);
                
            }else{
                JOptionPane.showMessageDialog(null, profesor.getUsuario());
                JOptionPane.showMessageDialog(null, profesor.getPassword());
                JOptionPane.showMessageDialog(null, "Credenciales no validas");
            }*/
            try {
                
                profesor = daoProfesor.validarSesion(usuario, password);
                if (profesor != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + profesor.getNombre());
                    JOptionPane.showMessageDialog(null,  profesor);
                    
                    login.dispose();
                    menuPrincipal.setVisible(true);
                } else {
                     JOptionPane.showMessageDialog(null, usuario);
                      JOptionPane.showMessageDialog(null, password);
                     JOptionPane.showMessageDialog(null,  profesor);
                    JOptionPane.showMessageDialog(null, "Credencianels invalidas");
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage());
            }

        }

    }

}

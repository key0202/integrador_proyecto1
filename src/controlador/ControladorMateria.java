package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.dao.DaoMateria;
import modelo.dto.Materia;
import vista.MenuPrincipal;
import vista.Registro_Materia;

public class ControladorMateria implements ActionListener {

    private DaoMateria daoMateria;//dao
    private Registro_Materia vista_materia;//vista
    private Materia materia = new Materia();//dto

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();

    //obtenemos nombre de usuario
    String docente = Controlador.docente;

    public ControladorMateria(Registro_Materia vista_materia, DaoMateria daoMateria) {
        this.vista_materia = vista_materia;
        this.daoMateria = daoMateria;
        this.vista_materia.setVisible(true);
        eventos();
    }

    //metodo para iniciar la aplicacion
    public void iniciar() {
        daoMateria.leer(vista_materia, docente);
    }
    
    public void eventos(){
        this.vista_materia.btnAgregar.addActionListener(this);
        this.vista_materia.btnRegresar.addActionListener(this);
    }
    
    //estos son los botones del registro de materias
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista_materia.btnAgregar) {
            String curso = vista_materia.jTextField1.getText();
            if (curso.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe agregar una materia");
            } else {
                materia.setNombreMateria(curso);
                daoMateria.agregarMateria(materia, vista_materia, docente);
            }
        }
        if (e.getSource() == vista_materia.btnRegresar) {
            //regresamos a MenuPrincipal
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vista_materia.dispose();
        }
    }

}

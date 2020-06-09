package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.dao.DaoAlumno;
import vista.MenuPrincipal;
import vista.Registro_Alumno;

/**
 *
 * @author DAVID
 */
public class ControlAlumno implements ActionListener {
    
    private DaoAlumno daoAlumno;//dao
    private Registro_Alumno vista_alumno;//vista

    //jframes a los que se dirigira
    MenuPrincipal menuPrincipal = new MenuPrincipal();
    
    public ControlAlumno(Registro_Alumno vista_alumno, DaoAlumno daoAlumno) {
        this.daoAlumno = daoAlumno;
        this.vista_alumno = vista_alumno;
        this.vista_alumno.jButton1.addActionListener(this);
        
        this.vista_alumno.setVisible(true);
    }

    //metodo para iniciar la aplicacion
    public void iniciar() {
        vista_alumno.setVisible(true);
        vista_alumno.setLocationRelativeTo(null);
        vista_alumno.setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //regresamos a MenuPrincipal
        if (e.getSource() == vista_alumno.jButton1) {
            ControladorMenu ctrlMenu = new ControladorMenu(menuPrincipal);
            ctrlMenu.menuPrincipal.setVisible(true);
            vista_alumno.dispose();
        }
    }
    
}

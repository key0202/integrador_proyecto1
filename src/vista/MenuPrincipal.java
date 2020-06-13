
package vista;


public class MenuPrincipal extends javax.swing.JFrame {


    public MenuPrincipal() {
        initComponents();
        this.setResizable(false);
        this.setTitle("Menu Principal");
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu5 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnRegistrarAlumno = new javax.swing.JButton();
        btnRegistrarAsistencia = new javax.swing.JButton();
        btnRegistrarMateria = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrarNotas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreProfesor = new javax.swing.JLabel();
        txtdniProfesor = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        txtNombreProfesor1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuSesion = new javax.swing.JMenu();
        menuItemCerrarSesion = new javax.swing.JMenuItem();

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Control de Notas y Asistencias de Alumnos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 80));

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrarAlumno.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/alumno.png"))); // NOI18N
        jPanel4.add(btnRegistrarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 140));

        btnRegistrarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario1.jpg"))); // NOI18N
        jPanel4.add(btnRegistrarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 140, 150));

        btnRegistrarMateria.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/curso.png"))); // NOI18N
        jPanel4.add(btnRegistrarMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 140, 140));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registrar Alumno");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registrar Asistencias");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Registrar Notas");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 90, 20));

        btnRegistrarNotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notas.png"))); // NOI18N
        jPanel4.add(btnRegistrarNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 140, 150));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Registrar Materia");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, 20));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 350, 590));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Datos de Usuario");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, 40));

        txtNombreProfesor.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtNombreProfesor.setForeground(new java.awt.Color(0, 204, 255));
        txtNombreProfesor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreProfesor.setText("Nombre");
        jPanel2.add(txtNombreProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 140, 30));

        txtdniProfesor.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtdniProfesor.setForeground(new java.awt.Color(0, 204, 255));
        txtdniProfesor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtdniProfesor.setText("DNI");
        jPanel2.add(txtdniProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 140, 30));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 204, 255));
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuario.setText("Nombre Usuario");
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 140, 30));

        txtNombreProfesor1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtNombreProfesor1.setForeground(new java.awt.Color(0, 204, 255));
        txtNombreProfesor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreProfesor1.setText("Paterno Materno");
        jPanel2.add(txtNombreProfesor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 140, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 670));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 670));

        menuSesion.setText("Sesion");

        menuItemCerrarSesion.setText("Cerrar sesion");
        menuSesion.add(menuItemCerrarSesion);

        jMenuBar1.add(menuSesion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistrarAlumno;
    public javax.swing.JButton btnRegistrarAsistencia;
    public javax.swing.JButton btnRegistrarMateria;
    public javax.swing.JButton btnRegistrarNotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JMenuItem menuItemCerrarSesion;
    public javax.swing.JMenu menuSesion;
    public javax.swing.JLabel txtNombreProfesor;
    public javax.swing.JLabel txtNombreProfesor1;
    public javax.swing.JLabel txtUsuario;
    public javax.swing.JLabel txtdniProfesor;
    // End of variables declaration//GEN-END:variables
}

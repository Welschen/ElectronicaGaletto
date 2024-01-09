package com.mycompany.electronicagaletto.igu;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnArtic = new javax.swing.JMenu();
        btnConsArt = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnEditArt = new javax.swing.JMenuItem();
        btnCliente = new javax.swing.JMenu();
        btnNuevoCliente = new javax.swing.JMenuItem();
        btnConsulCliente = new javax.swing.JMenuItem();
        btnEditCliente = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnDevol = new javax.swing.JMenu();
        btnGrupo = new javax.swing.JMenu();
        btnNuevoGrupo = new javax.swing.JMenuItem();
        btnConsulGrupo = new javax.swing.JMenuItem();
        btnEditGrupo = new javax.swing.JMenuItem();
        btnPago = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Electronica Galetto");

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnArtic.setText("Artículo");

        btnConsArt.setText("Nuevo");
        btnConsArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsArtActionPerformed(evt);
            }
        });
        btnArtic.add(btnConsArt);

        jMenuItem2.setText("Consultar");
        btnArtic.add(jMenuItem2);

        btnEditArt.setText("Editar");
        btnArtic.add(btnEditArt);

        jMenuBar1.add(btnArtic);

        btnCliente.setText("Cliente");

        btnNuevoCliente.setText("Nuevo");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        btnCliente.add(btnNuevoCliente);

        btnConsulCliente.setText("Consultar");
        btnCliente.add(btnConsulCliente);

        btnEditCliente.setText("Editar");
        btnEditCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditClienteActionPerformed(evt);
            }
        });
        btnCliente.add(btnEditCliente);

        jMenuBar1.add(btnCliente);

        jMenu3.setText("Venta");
        jMenuBar1.add(jMenu3);

        btnDevol.setText("Devolución");
        jMenuBar1.add(btnDevol);

        btnGrupo.setText("Grupo");

        btnNuevoGrupo.setText("Nuevo");
        btnNuevoGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoGrupoActionPerformed(evt);
            }
        });
        btnGrupo.add(btnNuevoGrupo);

        btnConsulGrupo.setText("Consultar");
        btnGrupo.add(btnConsulGrupo);

        btnEditGrupo.setText("Editar");
        btnGrupo.add(btnEditGrupo);

        jMenuBar1.add(btnGrupo);

        btnPago.setText("Pago");
        jMenuBar1.add(btnPago);

        jMenu1.setText("Localidad");

        jMenuItem1.setText("Nueva");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsArtActionPerformed
            CargaArticulo pantalla = new CargaArticulo();
            pantalla.setVisible(true);
            pantalla.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnConsArtActionPerformed

    private void btnNuevoGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoGrupoActionPerformed
            CargarGrupo  pantalla = new CargarGrupo();
            pantalla.setVisible(true);
            pantalla.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnNuevoGrupoActionPerformed

    private void btnEditClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditClienteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
            CargarLocalidad  pantalla = new CargarLocalidad();
            pantalla.setVisible(true);
            pantalla.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
            CargaCliente  pantalla = new CargaCliente();
            pantalla.setVisible(true);
            pantalla.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JMenu btnArtic;
    private javax.swing.JMenu btnCliente;
    private javax.swing.JMenuItem btnConsArt;
    private javax.swing.JMenuItem btnConsulCliente;
    private javax.swing.JMenuItem btnConsulGrupo;
    private javax.swing.JMenu btnDevol;
    private javax.swing.JMenuItem btnEditArt;
    private javax.swing.JMenuItem btnEditCliente;
    private javax.swing.JMenuItem btnEditGrupo;
    private javax.swing.JMenu btnGrupo;
    private javax.swing.JMenuItem btnNuevoCliente;
    private javax.swing.JMenuItem btnNuevoGrupo;
    private javax.swing.JMenu btnPago;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}

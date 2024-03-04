package com.mycompany.electronicagaletto;

//import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Usuario;
//import com.mycompany.electronicagaletto.view.ViewArticulos;
import com.mycompany.electronicagaletto.vista.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class ElectronicaGaletto extends javax.swing.JFrame {
    
    Usuario usr;
    ControladoraLogica control;
    
    public ElectronicaGaletto() {
        initComponents();
        initStyles();   
        initContent();
    }

    public ElectronicaGaletto(ControladoraLogica control, Usuario usr) {
        initComponents();
        initStyles();   
        initContent();
        this.usr = usr;
        this.control = control;
    }
    private void initStyles() {
        lblEmpresa.putClientProperty("FlatLaf.style", "font: bold $h2.regular.font");
        lblEmpresa.setForeground(Color.white);
    }
    
    private void initContent() {
        ShowJPanel(new VistaArticulos(usr));
    }

    public static void ShowJPanel(JPanel p) {
        p.setSize(1300, 725);
        p.setLocation(0, 0);
        
        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        lblEmpresa = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnArticulos = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnGrupos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnLocalidades = new javax.swing.JButton();
        btnDevoluciones = new javax.swing.JButton();
        btnDevoluciones1 = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setForeground(new java.awt.Color(0, 0, 0));
        background.setMinimumSize(new java.awt.Dimension(1100, 641));
        background.setPreferredSize(new java.awt.Dimension(1100, 641));

        menu.setBackground(new java.awt.Color(13, 71, 161));
        menu.setForeground(new java.awt.Color(0, 0, 0));
        menu.setMinimumSize(new java.awt.Dimension(270, 640));
        menu.setPreferredSize(new java.awt.Dimension(270, 640));

        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setText("Electrónica Galetto");

        btnArticulos.setBackground(new java.awt.Color(13, 71, 161));
        btnArticulos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnArticulos.setForeground(new java.awt.Color(255, 255, 255));
        btnArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tienda-alt.png"))); // NOI18N
        btnArticulos.setText("Artículos");
        btnArticulos.setToolTipText("");
        btnArticulos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnArticulos.setBorderPainted(false);
        btnArticulos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArticulos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnArticulos.setIconTextGap(14);
        btnArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArticulosActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(13, 71, 161));
        btnClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuarios2.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setToolTipText("");
        btnClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnClientes.setBorderPainted(false);
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.setIconTextGap(14);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnGrupos.setBackground(new java.awt.Color(13, 71, 161));
        btnGrupos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGrupos.setForeground(new java.awt.Color(255, 255, 255));
        btnGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/soportes-cuadrados (1).png"))); // NOI18N
        btnGrupos.setText("Grupos");
        btnGrupos.setToolTipText("");
        btnGrupos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnGrupos.setBorderPainted(false);
        btnGrupos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrupos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGrupos.setIconTextGap(14);
        btnGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGruposActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(13, 71, 161));
        btnVentas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carrito-de-compras (1).png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setToolTipText("");
        btnVentas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnVentas.setBorderPainted(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentas.setIconTextGap(14);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnReportes.setBackground(new java.awt.Color(13, 71, 161));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivar-factura (1).png"))); // NOI18N
        btnReportes.setText("Reportes");
        btnReportes.setToolTipText("");
        btnReportes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnReportes.setBorderPainted(false);
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.setIconTextGap(14);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnLocalidades.setBackground(new java.awt.Color(13, 71, 161));
        btnLocalidades.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLocalidades.setForeground(new java.awt.Color(255, 255, 255));
        btnLocalidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivar-factura (1).png"))); // NOI18N
        btnLocalidades.setText("Localidades");
        btnLocalidades.setToolTipText("");
        btnLocalidades.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnLocalidades.setBorderPainted(false);
        btnLocalidades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLocalidades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLocalidades.setIconTextGap(14);
        btnLocalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalidadesActionPerformed(evt);
            }
        });

        btnDevoluciones.setBackground(new java.awt.Color(13, 71, 161));
        btnDevoluciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDevoluciones.setForeground(new java.awt.Color(255, 255, 255));
        btnDevoluciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivar-factura (1).png"))); // NOI18N
        btnDevoluciones.setText("Devoluciones");
        btnDevoluciones.setToolTipText("");
        btnDevoluciones.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnDevoluciones.setBorderPainted(false);
        btnDevoluciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDevoluciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDevoluciones.setIconTextGap(14);
        btnDevoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucionesActionPerformed(evt);
            }
        });

        btnDevoluciones1.setBackground(new java.awt.Color(13, 71, 161));
        btnDevoluciones1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDevoluciones1.setForeground(new java.awt.Color(255, 255, 255));
        btnDevoluciones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/archivar-factura (1).png"))); // NOI18N
        btnDevoluciones1.setText("Cuentas corrientes");
        btnDevoluciones1.setToolTipText("");
        btnDevoluciones1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnDevoluciones1.setBorderPainted(false);
        btnDevoluciones1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDevoluciones1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDevoluciones1.setIconTextGap(14);
        btnDevoluciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevoluciones1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnDevoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnDevoluciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnDevoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnDevoluciones1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setMinimumSize(new java.awt.Dimension(750, 100));
        header.setPreferredSize(new java.awt.Dimension(750, 100));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setMinimumSize(new java.awt.Dimension(1400, 800));
        content.setPreferredSize(new java.awt.Dimension(1400, 800));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1697, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1710, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 1697, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArticulosActionPerformed
        ShowJPanel(new VistaArticulos(usr));
    }//GEN-LAST:event_btnArticulosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        ShowJPanel(new VistaClientes(usr));
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGruposActionPerformed
        ShowJPanel(new VistaGrupos( usr));
    }//GEN-LAST:event_btnGruposActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        ShowJPanel(new VistaVentas(usr));
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnLocalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalidadesActionPerformed
        ShowJPanel(new VistaLocalidades());
    }//GEN-LAST:event_btnLocalidadesActionPerformed

    private void btnDevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucionesActionPerformed
        ShowJPanel(new VistaDevolucion(usr));
    }//GEN-LAST:event_btnDevolucionesActionPerformed

    private void btnDevoluciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevoluciones1ActionPerformed
        ShowJPanel(new CuentasClientes(usr));
    }//GEN-LAST:event_btnDevoluciones1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMaterialLighterIJTheme.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            //new ElectronicaGaletto().setVisible(true);
            new Login().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnArticulos;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnDevoluciones;
    private javax.swing.JButton btnDevoluciones1;
    private javax.swing.JButton btnGrupos;
    private javax.swing.JButton btnLocalidades;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnVentas;
    private static javax.swing.JPanel content;
    private javax.swing.JPanel header;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
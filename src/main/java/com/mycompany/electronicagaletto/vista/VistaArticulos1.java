package com.mycompany.electronicagaletto.vista;


import static com.mycompany.electronicagaletto.ElectronicaGaletto1.ShowJPanel1;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class VistaArticulos1 extends javax.swing.JPanel {

    private TableRowSorter<DefaultTableModel> sorter;
    ControladoraLogica control = null;
    Usuario usr;
    DecimalFormat df = new DecimalFormat("#.00");
    public VistaArticulos1( Usuario usr) {
        control = new ControladoraLogica();
        initComponents();
        initStyles();
        cargarTabla();
        this.usr = usr;
        tablaArticulos.getColumnModel().getColumn(3).setCellRenderer(new RenderPintar1());
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el artículo a buscar.");
    }
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
        //nombres de las columnas
    String titulos[] = {"Identificador","Nombre", "Precio($)", "Stock","Stock mínimo", "Grupo", 
        "Código de barras", "Estado"};
    datosTabla.setColumnIdentifiers(titulos);
        //traer datos desde la base
    List <Articulo> listaArticulos = control.traerArticulos();
        //recorrer la lista y mostrar datos en la tabla
    if (listaArticulos!=null){
        for (Articulo arti : listaArticulos) {
            
            String con1 =df.format(arti.getPrecio());
            String sin1=con1.replace(',', '.');
            Object[] objeto = {arti.getIdArticulo(), arti.getNombreArticulo(),
                sin1, arti.getStock(), arti.getGrupo().getBajoStock(),
            arti.getGrupo().getNombreGrupo(), arti.getCodBarra(), arti.getEstado()};
            
            datosTabla.addRow(objeto);
        }   
    }
    tablaArticulos.setModel(datosTabla);
        JTableHeader thead = tablaArticulos.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Segoe UI", Font.BOLD, 14));
    
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
      
        // Aplicar las alineaciones a las columnas específicas
        tablaArticulos.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        //tablaArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tablaArticulos.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tablaArticulos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tablaArticulos.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tablaArticulos.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
       
    
      
    tablaArticulos.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tablaArticulos.setRowSorter(sorter);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArticulos = new javax.swing.JTable();
        btnEditar1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(817, 528));
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Artículos");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscar.setBorder(null);
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tablaArticulos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaArticulosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaArticulos);

        btnEditar1.setBackground(new java.awt.Color(13, 71, 161));
        btnEditar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar1.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar1.setText("Artículos con stock bajo ");
        btnEditar1.setBorder(null);
        btnEditar1.setBorderPainted(false);
        btnEditar1.setMaximumSize(new java.awt.Dimension(44, 20));
        btnEditar1.setMinimumSize(new java.awt.Dimension(44, 20));
        btnEditar1.setPreferredSize(new java.awt.Dimension(44, 20));
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addGap(603, 603, 603))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtBuscar))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorResized

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tablaArticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaArticulosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaArticulosKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
       ShowJPanel1(new ArticulosBajoStock1(usr));
    }//GEN-LAST:event_btnEditar1ActionPerformed
    private void filtrar(){
        String texto = txtBuscar.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaArticulos;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}

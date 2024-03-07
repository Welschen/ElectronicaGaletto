package com.mycompany.electronicagaletto.vista;

import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.Articulo;

import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Usuario;

import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



public class IngresoMercaderia extends javax.swing.JPanel {
    ControladoraLogica control =null;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    private TableRowSorter<DefaultTableModel> sorter;
    Usuario usr;
   
   // DecimalFormat df = new DecimalFormat(".00");
    public IngresoMercaderia(Usuario usr) {
        control = new ControladoraLogica();
        this.usr=usr;
        initComponents();
        initStyles();
        cargarTabla();
        llenarTabla();
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtArtic.putClientProperty("JTextField.placeholderText", "Ingrese el artículo que desea buscar.");
    }
 
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false;
            }
    };
    String titulos[] = {"Identificador", "Nombre", "Precio", "Stock actual","Código de barras"};
    datosTabla.setColumnIdentifiers(titulos);
    //traer datos desde la base
    List <Articulo> listaArticulos = control.traerArticulos();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaArticulos!=null){
        for (Articulo arti : listaArticulos) {
            if(arti.isEstado()){
           // String pre =df.format(arti.getPrecio());
            Object[] objeto = {arti.getIdArticulo(), arti.getNombreArticulo(),arti.getPrecio(), arti.getStock(),
             arti.getCodBarra()};
            
            datosTabla.addRow(objeto);
            }
        }   
    }
    
    tblArticulos.setModel(datosTabla);
     DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
       
        // Aplicar las alineaciones a las columnas específicas
        tblArticulos.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
       // tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblArticulos.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
    tblArticulos.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tblArticulos.setRowSorter(sorter);
    }
    
    
     private void llenarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador", "Nombre", "Precio", "Stock nuevo", "Stock anterior"};
    datosTabla.setColumnIdentifiers(titulos);
  
    tblNuevoStock.setModel(datosTabla);
     DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tblNuevoStock.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
       // tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblNuevoStock.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblNuevoStock.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
        tblNuevoStock.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
       // tablaClientes.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnAgregarArt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNuevoStock = new javax.swing.JTable();
        txtArtic = new javax.swing.JTextField();
        btnConfirmarVenta = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        btnLimpTodo = new javax.swing.JButton();
        btnLimpiarUno = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(817, 528));
        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(817, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(817, 528));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Ingreso de mercaderia");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Artículo:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cantidad:");

        txtCant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setActionCommand("<Not Set>");
        txtCant.setBorder(null);
        txtCant.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });

        btnAgregarArt.setBackground(new java.awt.Color(13, 71, 161));
        btnAgregarArt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarArt.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarArt.setText("Agregar");
        btnAgregarArt.setBorder(null);
        btnAgregarArt.setBorderPainted(false);
        btnAgregarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarArtActionPerformed(evt);
            }
        });

        tblNuevoStock.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tblNuevoStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblNuevoStock);

        txtArtic.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtArtic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtArticKeyReleased(evt);
            }
        });

        btnConfirmarVenta.setBackground(new java.awt.Color(13, 71, 161));
        btnConfirmarVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfirmarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarVenta.setText("Confirmar");
        btnConfirmarVenta.setBorder(null);
        btnConfirmarVenta.setBorderPainted(false);
        btnConfirmarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarVentaActionPerformed(evt);
            }
        });

        tblArticulos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticulosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblArticulos);

        btnLimpTodo.setBackground(new java.awt.Color(13, 71, 161));
        btnLimpTodo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpTodo.setText("Limpiar todo");
        btnLimpTodo.setBorder(null);
        btnLimpTodo.setBorderPainted(false);
        btnLimpTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpTodoActionPerformed(evt);
            }
        });

        btnLimpiarUno.setBackground(new java.awt.Color(13, 71, 161));
        btnLimpiarUno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiarUno.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarUno.setText("Quitar un artículo");
        btnLimpiarUno.setBorder(null);
        btnLimpiarUno.setBorderPainted(false);
        btnLimpiarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarUnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnLimpiarUno, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtArtic, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                        .addGap(3, 3, 3)))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtArtic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarUno, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarArtActionPerformed
        int fila = tblArticulos.getSelectedRow();
        if(fila !=-1){
            
            int cant = Integer.parseInt(txtCant.getText());
            int stock =Integer.parseInt(tblArticulos.getValueAt(fila, 3).toString());
            int nuevo = cant+stock;
                        
            Object[] obj = {tblArticulos.getValueAt(fila, 0), tblArticulos.getValueAt(fila, 1),
            tblArticulos.getValueAt(fila, 2),nuevo, stock};
            
            DefaultTableModel modelo = (DefaultTableModel)tblNuevoStock.getModel();
    
            modelo.addRow(obj);
            txtArtic.setText(" ");
            txtCant.setText("1");
            
        }else{
            JOptionPane optionPane = new JOptionPane("Debe seleccionar un artículo");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnAgregarArtActionPerformed

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void btnConfirmarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarVentaActionPerformed
         if (tblNuevoStock.getRowCount()>0){
             for (int i=0; i <tblNuevoStock.getRowCount(); i++){
                 //Articulo art =(Articulo) tblVenta.getValueAt(i, 0);
                 int idArt = Integer.parseInt(tblNuevoStock.getValueAt(i, 0).toString());
                 int nuev =Integer.parseInt(tblNuevoStock.getValueAt(i, 3).toString());
                 Articulo art = control.traerArticulo(idArt);
                 control.editStock(art,nuev,usr);
                 
                 
             }
              JOptionPane optionPane = new JOptionPane("El stock de los artículos a sido modificado correctamente");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Información");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
             }else{
              JOptionPane optionPane = new JOptionPane("Debe seleccionar un artículo");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
         }
         ShowJPanel(new IngresoMercaderia(usr));
    }//GEN-LAST:event_btnConfirmarVentaActionPerformed
    
    private void txtArticKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticKeyReleased
        filtrar();
    }//GEN-LAST:event_txtArticKeyReleased
    
    private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked
        int f = tblArticulos.getSelectedRow();
        String art = tblArticulos.getValueAt(f, 1).toString();
        txtArtic.setText(art);
    }//GEN-LAST:event_tblArticulosMouseClicked

    private void btnLimpTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpTodoActionPerformed
        llenarTabla();
        cargarTabla();
        txtArtic.setText(" ");
    }//GEN-LAST:event_btnLimpTodoActionPerformed

    private void btnLimpiarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUnoActionPerformed
        int num =tblNuevoStock.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tblNuevoStock.getModel();
        model.removeRow(num);
        
    }//GEN-LAST:event_btnLimpiarUnoActionPerformed
     
   
    private void filtrar(){
        String texto = txtArtic.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarArt;
    private javax.swing.JButton btnConfirmarVenta;
    private javax.swing.JButton btnLimpTodo;
    private javax.swing.JButton btnLimpiarUno;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblNuevoStock;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtArtic;
    private javax.swing.JTextField txtCant;
    // End of variables declaration//GEN-END:variables

   
}

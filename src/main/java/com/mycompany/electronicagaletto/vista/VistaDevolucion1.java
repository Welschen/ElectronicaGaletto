package com.mycompany.electronicagaletto.vista;
import com.mycompany.electronicagaletto.ElectronicaGaletto1;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.ItemVenta;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;


public class VistaDevolucion1 extends javax.swing.JPanel {
    private TableRowSorter<DefaultTableModel> sorter2;
    ControladoraLogica control =null;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    private TableRowSorter<DefaultTableModel> sorter;
    Usuario usr;
    Cliente cliente;
    Devolucion devol;
    DecimalFormat df = new DecimalFormat("0.00"); 
    double suma;
    public VistaDevolucion1(Usuario usr) {
        control = new ControladoraLogica();
        this.usr=usr;
        initComponents();
        initStyles();
        cargarTablaClientes();
        //cargarTabla();
        llenarTabla();
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtCliente.putClientProperty("JTextField.placeholderText", "Ingrese el nombre, apellido o DNI del cliente");
        txtTotal.putClientProperty("JTextField.placeholderText", "$");
    }
     private void cargarTablaClientes(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador", "Apellido", "Nombre", "DNI"};
    datosTabla.setColumnIdentifiers(titulos);
     //traer datos desde la base
    List <Cliente> listaCliente = control.traerClientesActivo();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaCliente!=null){
        for (Cliente cli : listaCliente) {
            Object[] objeto = {cli.getIdCliente(), cli.getApellido(), cli.getNombre(), cli.getDni()};
            
            datosTabla.addRow(objeto);
        }   
    }
    tablaClientes.setModel(datosTabla);
    
    JTableHeader thead = tablaClientes.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tablaClientes.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        //tablaClientes.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        //tablaClientes.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
        tablaClientes.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
    tablaClientes.setAutoCreateRowSorter(true);
    sorter2 = new TableRowSorter<>(datosTabla);
    tablaClientes.setRowSorter(sorter2);
    }
    private void cargarTabla(){
        DefaultTableModel datosTabla = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador", "Nombre","Código de barras", "Precio unitario ($)", 
        "Cantidad", "Subtotal ($)"};
    datosTabla.setColumnIdentifiers(titulos);
   
    //traer datos desde la base
    List <ItemVenta> listaItemV = control.traerItemVenta(cliente);
    
        //recorrer la lista y mostrar datos en la tabla
    if (!listaItemV.isEmpty()){
        for (ItemVenta item : listaItemV ) {
            int can = item.getCantidad();
             String con =df.format(item.getPrecioVta());
               String sinTot=con.replace(',', '.');
            double subt = item.getPrecioVta();
            double pu=subt/can;
            String con1 =df.format(pu);
               String sin1=con1.replace(',', '.');
            Object[] objeto = {item.getArticu().getIdArticulo(), item.getArticu().getNombreArticulo(), 
                item.getArticu().getCodBarra(), sin1, can, sinTot};
             
            
            datosTabla.addRow(objeto);
        }   
    }else{
            JOptionPane optionPane = new JOptionPane("No se registraron ventas para el cliente "+cliente.getNombre()+" "+cliente.getApellido());
             optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);  
             JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
    }
    
    tblArticulos.setModel(datosTabla);
    
    JTableHeader thead = tblArticulos.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Segoe UI", Font.BOLD, 14));
    DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tblArticulos.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblArticulos.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tblArticulos.getColumnModel().getColumn(5).setCellRenderer(alinearDerecha);
       // tablaClientes.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
    tblArticulos.setAutoCreateRowSorter(true);
    sorter = new TableRowSorter<>(datosTabla);
    tblArticulos.setRowSorter(sorter);
    }
    
     private void llenarTabla(){
        DefaultTableModel datosTabla2 = new DefaultTableModel(){
           
            @Override
            public boolean isCellEditable (int row, int column){
               return false; 
            }
    };
    String titulos[] = {"Identificador", "Nombre","Código de barras","Estado",
        "Precio unitario ($)", "Cantidad", "Subtotal ($)"};
    datosTabla2.setColumnIdentifiers(titulos);
  
    tblVenta.setModel(datosTabla2);
    
    JTableHeader thead = tblVenta.getTableHeader();
        thead.setForeground(Color.BLACK);
        thead.setFont(new Font("Segoe UI", Font.BOLD, 14));
        DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tblVenta.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblVenta.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(5).setCellRenderer(alinearDerecha);
        
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        btnAgregarArt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        txtArtic = new javax.swing.JTextField();
        btnConfirmarVenta = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rbtnCambio = new javax.swing.JRadioButton();
        txtCliente = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(817, 528));
        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(817, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(817, 528));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Devoluciones");

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
        jScrollPane1.setViewportView(tblArticulos);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cliente:");

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
        txtCant.setText("1");
        txtCant.setActionCommand("<Not Set>");
        txtCant.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
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

        tblVenta.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblVenta);

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

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Total:");

        rbtnCambio.setBackground(new java.awt.Color(255, 255, 255));
        rbtnCambio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtnCambio.setForeground(new java.awt.Color(0, 0, 0));
        rbtnCambio.setSelected(true);
        rbtnCambio.setText("Cambio de artículo");

        txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        tablaClientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addGap(517, 517, 517))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(rbtnCambio))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtArtic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(rbtnCambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtArtic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarArtActionPerformed
        int fila = tblArticulos.getSelectedRow();
        if(fila !=-1){
            
            int cant = Integer.parseInt(txtCant.getText());
            int canVenta =Integer.parseInt(tblArticulos.getValueAt(fila, 4).toString());
            if(cant<=canVenta){
                double precio =Double.valueOf(tblArticulos.getValueAt(fila, 3).toString());
           
            String estado;
            if(rbtnCambio.isSelected()){
                estado="Cambio";
            }else
                estado="Devolución";
            if( "Cambio".equals(estado)){
                precio=0;
            }
             double subtot = cant * precio;
             String con =df.format(subtot);
               String sinTot=con.replace(',', '.');
               String con1 =df.format(precio);
               String sin1=con1.replace(',', '.');
            Object[] obj = {tblArticulos.getValueAt(fila, 0), tblArticulos.getValueAt(fila, 1),
            tblArticulos.getValueAt(fila, 2),estado,sin1, cant, sinTot};
            int canVentaNuevo=canVenta-cant;
            tblArticulos.setValueAt(canVentaNuevo,fila, 4);
            double subtNuevo= canVentaNuevo*precio;
            tblArticulos.setValueAt(subtNuevo,fila, 5);
            DefaultTableModel modelo = (DefaultTableModel)tblVenta.getModel();
             suma = subtot;
                for(int i=0; i<modelo.getRowCount(); i++) {
            suma += Double.parseDouble(modelo.getValueAt(i, 6).toString()); 
                }
                String s =df.format(suma);
               String sum=s.replace(',', '.');
           txtTotal.setText("$ "+String.valueOf(sum));
            modelo.addRow(obj);
            txtCant.setText("1");
            rbtnCambio.setSelected(true);
            
            }else{
              JOptionPane optionPane = new JOptionPane("La cantidad del artículo registrado en la venta es menor a la cantidad que desea devolver o cambiar");
             optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);  
             JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            }
        }else{
            JOptionPane optionPane = new JOptionPane("Debe seleccionar un artículo");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnAgregarArtActionPerformed
    private void filtrar2(){
        String texto = txtCliente.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter2.setRowFilter(RowFilter.regexFilter(filtro));
    }
    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void btnConfirmarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarVentaActionPerformed
         if (tblVenta.getRowCount()>0){
             double tot = suma;
             control.guardarDevol(usr,cliente,tot);
             int idDevo = control.traeridDevol();
             devol = control.traerDevolucion(idDevo);
             
             //GUARDAR LOS ITEMdevol OBTENIDOS DE LA TABLA CON UN FOR
             for (int i=0; i <tblVenta.getRowCount(); i++){
                 
                 int idArt = Integer.parseInt(tblVenta.getValueAt(i, 0).toString());
                 Articulo art = control.traerArticulo(idArt);
                 double subTot = Double.valueOf(tblVenta.getValueAt(i, 6).toString());
                 boolean esCambio;
                 String state = (String) tblVenta.getValueAt(i, 3);
                 if("Cambio".equals(state)){
                 esCambio = true;
                }else{
                     esCambio = false;
                 }
                 int cant = Integer.parseInt(tblVenta.getValueAt(i, 5).toString());
                 
                 control.guardarItemDevolucion(cant, subTot, devol, art, esCambio);
                  
             }
             JOptionPane optionPane = new JOptionPane("La devolución fue registrada correctamente");
                  optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);  
                  JDialog dialog = optionPane.createDialog("Información");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                ElectronicaGaletto1.ShowJPanel1(new VistaDevolucion1(usr));
                
             }
    }//GEN-LAST:event_btnConfirmarVentaActionPerformed
    
    private void txtArticKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticKeyReleased
        filtrar();
    }//GEN-LAST:event_txtArticKeyReleased

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        filtrar2();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        int f = tablaClientes.getSelectedRow();

        String client = tablaClientes.getValueAt(f, 0).toString()+" - "+
        tablaClientes.getValueAt(f, 1).toString()+" "+
        tablaClientes.getValueAt(f, 2).toString()+" - "+
        tablaClientes.getValueAt(f, 3).toString();
        txtCliente.setText(client);

        int id =(int) tablaClientes.getValueAt(f, 0);
        cliente = control.traerCliente(id);
        cargarTabla();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
        int key= evt.getKeyChar();
        boolean numero = key>= 48 && key<=57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyTyped
     private void filtrar(){
        String texto = txtArtic.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarArt;
    private javax.swing.JButton btnConfirmarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbtnCambio;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblVenta;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtArtic;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

   

}

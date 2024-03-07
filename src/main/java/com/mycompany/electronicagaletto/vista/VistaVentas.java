package com.mycompany.electronicagaletto.vista;

import static com.mycompany.electronicagaletto.ElectronicaGaletto.ShowJPanel;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.ControladoraLogica;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import java.text.DecimalFormat;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;



public class VistaVentas extends javax.swing.JPanel{
    ControladoraLogica control =null;
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    private TableRowSorter<DefaultTableModel> sorter;
    private TableRowSorter<DefaultTableModel> sorter2;
    Usuario usr;
    Cliente cliente= null;
    DecimalFormat df = new DecimalFormat("0.00"); 
    double suma=0;
    public VistaVentas(Usuario usr) {
        control = new ControladoraLogica();
        this.usr=usr;
        initComponents();
        initStyles();
        cargarTabla();
        cargarTablaClientes();
        llenarTabla();
        tblArticulos.getColumnModel().getColumn(4).setCellRenderer(new RenderPintar());
    }
    private void initStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtCliente.putClientProperty("JTextField.placeholderText", "Ingrese el nombre, apellido o DNI del cliente");
        txtTotal.putClientProperty("JTextField.placeholderText", "$");
        txtArtic.putClientProperty("JTextField.placeholderText", "Ingrese el artículo a buscar");
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
     String titulos[] = {"Identificador", "Nombre", "Precio" ,"Código de barras","Stock", "Stock mínimo"};
        datosTabla.setColumnIdentifiers(titulos);
    //traer datos desde la base
    List <Articulo> listaArticulos = control.traerArticulos();
    
        //recorrer la lista y mostrar datos en la tabla
    if (listaArticulos!=null){
        for (Articulo arti : listaArticulos) {
            if(arti.isEstado()){
             String con =df.format(arti.getPrecio());
            String sin=con.replace(',', '.');
            Object[] objeto = {arti.getIdArticulo(), arti.getNombreArticulo(),sin,arti.getCodBarra(), arti.getStock(),
             arti.getGrupo().getBajoStock()};
            
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
        tblArticulos.getColumnModel().getColumn(5).setCellRenderer(alinearDerecha);
        
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
    String titulos[] = {"Identificador", "Nombre", "Precio unitario", "Cantidad", "Subtotal"};
    datosTabla.setColumnIdentifiers(titulos);
  
    tblVenta.setModel(datosTabla);
     DefaultTableCellRenderer alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
        // Aplicar las alineaciones a las columnas específicas
        tblVenta.getColumnModel().getColumn(0).setCellRenderer(alinearDerecha);
       // tblArticulos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
        tblVenta.getColumnModel().getColumn(2).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(3).setCellRenderer(alinearDerecha);
        tblVenta.getColumnModel().getColumn(4).setCellRenderer(alinearDerecha);
       // tablaClientes.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        txtCliente = new javax.swing.JTextField();
        btnLimpTodo = new javax.swing.JButton();
        btnLimpiarUno = new javax.swing.JButton();
        btnReporteVenta = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(817, 528));
        setPreferredSize(new java.awt.Dimension(817, 528));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(817, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(817, 528));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("Ventas");

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

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Total:");

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

        txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

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

        btnReporteVenta.setBackground(new java.awt.Color(13, 71, 161));
        btnReporteVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReporteVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteVenta.setText("Reportes");
        btnReporteVenta.setBorder(null);
        btnReporteVenta.setBorderPainted(false);
        btnReporteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(705, 705, 705))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiarUno, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtArtic)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCliente)))))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtArtic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiarUno, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
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
            int stock =Integer.parseInt(tblArticulos.getValueAt(fila, 4).toString());
            if(cant<=stock){
                double precio =Double.valueOf( tblArticulos.getValueAt(fila, 2).toString());
                String con =df.format(precio);
              String sin=con.replace(',', '.');
            double subtot = cant * precio;
            String con1 =df.format(subtot);
            String sin1=con.replace(',', '.');
            Object[] obj = {tblArticulos.getValueAt(fila, 0), tblArticulos.getValueAt(fila, 1),
            sin, cant, sin1};
            int stockNuevo=stock-cant;
            tblArticulos.setValueAt(stockNuevo,fila, 3);
            DefaultTableModel modelo = (DefaultTableModel)tblVenta.getModel();
            suma = subtot;
                for(int i=0; i<modelo.getRowCount(); i++) {
            suma = suma+Double.parseDouble(modelo.getValueAt(i, 4).toString());  
            }      
                
                 String con3 =df.format(suma);
              String sin3=con3.replace(',', '.');
            txtTotal.setText("$ "+sin3);
            modelo.addRow(obj);
            txtCant.setText("1");
            txtArtic.setText(" ");
            }else{
              JOptionPane optionPane = new JOptionPane("No hay stock suficiente");
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

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void btnConfirmarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarVentaActionPerformed
         if (tblVenta.getRowCount()>0 && cliente!=null){
             
             
             control.guardarVenta(usr,cliente,suma);
             int idVent = control.traeridVenta();
             Venta iDVenta = control.traerVenta(idVent);
            
             for (int i=0; i <tblVenta.getRowCount(); i++){
                
                 int idArt = Integer.parseInt(tblVenta.getValueAt(i, 0).toString());
                 Articulo art = control.traerArticulo(idArt);
                 double subTot = Double.parseDouble(tblVenta.getValueAt(i, 4).toString());
                 int cant = Integer.parseInt(tblVenta.getValueAt(i, 3).toString());
                 control.guardarItemVenta(cant, subTot, iDVenta, art);
                 int st =art.getStock();
                 int stMin = art.getGrupo().getBajoStock();
                 if(st-stMin==0){
                     alerta0(art);
                 }else if(st<=stMin){
                     alerta(art);
                 }
                 ShowJPanel(new PagoVenta(usr, iDVenta, cliente));
             }
             }else{
             JOptionPane optionPane = new JOptionPane("Debe seleccionar un cliente y agregar al menos un artículo");
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Error");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
         }
    }//GEN-LAST:event_btnConfirmarVentaActionPerformed
    
    private void txtArticKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArticKeyReleased
        filtrar();
    }//GEN-LAST:event_txtArticKeyReleased
    
    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        int f = tablaClientes.getSelectedRow();
        String client = tablaClientes.getValueAt(f, 0).toString()+" - "+
                tablaClientes.getValueAt(f, 1).toString()+" "+
                tablaClientes.getValueAt(f, 2).toString()+" - "+
                tablaClientes.getValueAt(f, 3).toString();
        txtCliente.setText(client);
        int id =(int) tablaClientes.getValueAt(f, 0);
        cliente = control.traerCliente(id);
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        filtrar2();
    }//GEN-LAST:event_txtClienteKeyReleased

    private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked
        int f = tblArticulos.getSelectedRow();
        String art = tblArticulos.getValueAt(f, 1).toString();
        txtArtic.setText(art);
    }//GEN-LAST:event_tblArticulosMouseClicked

    private void btnLimpTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpTodoActionPerformed
        llenarTabla();
        cargarTabla();
        txtTotal.setText("$ 0.00");
    }//GEN-LAST:event_btnLimpTodoActionPerformed

    private void btnLimpiarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUnoActionPerformed
        int num =tblVenta.getSelectedRow();
        int idArt = (int) tblVenta.getValueAt(num, 0);
        int cant = (int) tblVenta.getValueAt(num, 3);
        retornarStock(idArt,cant,num);
    }//GEN-LAST:event_btnLimpiarUnoActionPerformed

    private void btnReporteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteVentaActionPerformed
        ShowJPanel(new VerVentas(usr,cliente));
    }//GEN-LAST:event_btnReporteVentaActionPerformed
     private void retornarStock(int idArt, int cant, int num) {
         int i=0;
         boolean encontrado = false;
         DefaultTableModel modelo = (DefaultTableModel)tblArticulos.getModel();
        while (i < modelo.getRowCount() && !encontrado){
            if((int)tblArticulos.getValueAt(i, 0)==idArt){
                int stock=(int)tblArticulos.getValueAt(i, 3);
                int nuevo = stock+cant;
                tblArticulos.setValueAt(nuevo, i, 3);
                DefaultTableModel model = (DefaultTableModel)tblVenta.getModel();
                model.removeRow(num);
                double total=0;
                  for(int a=0; a<model.getRowCount(); a++){
                      total=total+ (double)tblVenta.getValueAt(i, 4);
                  }
                  String tot = String.valueOf(total);
                  txtTotal.setText("$ "+tot);
                encontrado=true;
            }else
           {
                 i++;
             }
        }
     }
      private void alerta(Articulo art) {
                JOptionPane.showConfirmDialog(null, "Se registra stock bajo de "
                 +art.getNombreArticulo() +"\n Puede actualizarlo en 'Ingreso de mercadería'", 
                 "Alerta de stock", JOptionPane.INFORMATION_MESSAGE);
    } private void alerta0(Articulo art) {
                JOptionPane.showConfirmDialog(null, "Ya no cuenta con stock de "
                 +art.getNombreArticulo() +"\n Puede cargarlo en 'Ingreso de mercadería'", 
                 "Alerta de stock", JOptionPane.INFORMATION_MESSAGE);
    }
    private void filtrar(){
        String texto = txtArtic.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
      private void filtrar2(){
        String texto = txtCliente.getText();
        String filtro = "(?i)" + texto; // agrega la flag (?i)
        sorter2.setRowFilter(RowFilter.regexFilter(filtro));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarArt;
    private javax.swing.JButton btnConfirmarVenta;
    private javax.swing.JButton btnLimpTodo;
    private javax.swing.JButton btnLimpiarUno;
    private javax.swing.JButton btnReporteVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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

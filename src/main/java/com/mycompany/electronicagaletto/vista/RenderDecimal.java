
package com.mycompany.electronicagaletto.vista;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class RenderDecimal extends DefaultTableCellRenderer{
    DecimalFormat formatter = new DecimalFormat( "#.00" );
    String formattedString;
    public RenderDecimal(){
    super();
    formatter.setMinimumFractionDigits(2);
}
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel labelResultado =(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        if((Double)value instanceof Double){
             
        value = formatter.format((Double)value);
        
    }

        
        return labelResultado;
    }
    
}

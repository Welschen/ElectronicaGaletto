
package com.mycompany.electronicagaletto.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderPintar1 extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel labelResultado =(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int min=Integer.valueOf(table.getValueAt(row, 4).toString());
        int stock=(int)value;
        if(stock==0){
            labelResultado.setForeground(Color.red);
            labelResultado.setBackground(Color.LIGHT_GRAY);
            labelResultado.setHorizontalAlignment(RIGHT);
        }
        else if(stock<=min){
            labelResultado.setForeground(Color.yellow);
            labelResultado.setBackground(Color.LIGHT_GRAY);
            labelResultado.setHorizontalAlignment(RIGHT);
        }else{
            labelResultado.setForeground(Color.GREEN);
            labelResultado.setHorizontalAlignment(RIGHT);
            labelResultado.setBackground(Color.LIGHT_GRAY);
        }
            
        return labelResultado;
    }
    
}

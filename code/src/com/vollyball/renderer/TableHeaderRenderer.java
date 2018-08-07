/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author nishant.vibhute
 */
public class TableHeaderRenderer implements TableCellRenderer {
    
    DefaultTableCellRenderer renderer;
    
    public TableHeaderRenderer(JTable table) {
        renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
        JLabel lbl = (JLabel) renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        lbl.setBorder(BorderFactory.createEmptyBorder());
        lbl.setForeground(Color.WHITE);
        lbl.setBackground(new Color(57, 74, 108));
        return lbl;
    }
}

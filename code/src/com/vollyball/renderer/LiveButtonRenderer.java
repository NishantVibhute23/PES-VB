/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author nishant.vibhute
 */
public class LiveButtonRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (value instanceof JPanel) {
            //This time return only the JLabel without icon
            JLabel l = new JLabel("");
            l.setForeground(Color.WHITE);
            l.setHorizontalAlignment(CENTER);
            l.setCursor(new Cursor(Cursor.HAND_CURSOR));
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button_evaluate.png")));
            JPanel p = (JPanel) value;
            p.setLayout(new BorderLayout());
            p.add(l, BorderLayout.CENTER);
            p.setBackground(Color.WHITE);
//            p.setBorder(new LineBorder(Color.WHITE, 4));
            return p;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

    }
}

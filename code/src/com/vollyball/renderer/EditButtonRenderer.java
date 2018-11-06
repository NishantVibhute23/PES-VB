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
import java.awt.Font;
import javax.swing.ImageIcon;
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
public class EditButtonRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (value instanceof JPanel) {
            //This time return only the JLabel without icon
            JLabel l = new JLabel("");
//            l.setForeground(Color.WHITE);
            l.setHorizontalAlignment(CENTER);
//            l.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 12));
            l.setCursor(new Cursor(Cursor.HAND_CURSOR));
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button_edit.png")));
            JPanel p = (JPanel) value;
            p.setLayout(new BorderLayout());
            p.add(l, BorderLayout.CENTER);
            p.setBackground(Color.WHITE);
            p.setCursor(new Cursor(Cursor.HAND_CURSOR));
            p.setBorder(new LineBorder(Color.WHITE, 2));
            return p;
        } else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

    }
}

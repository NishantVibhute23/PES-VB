/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.renderer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nishant.vibhute
 */
public class GroupableHeaderExample extends JFrame {

    GroupableHeaderExample() {

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{
            {"119", "foo", "bar", "ja", "ko", "zh", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt", "bar", "ja", "ko", "zh"}},
                new Object[]{"SNo.", "Player Name", "Matches Played", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        JTable table = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        TableColumnModel cm = table.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));

        GroupableTableHeader header = (GroupableTableHeader) table.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(table);
//        getContentPane().add(scroll);
//        setSize(400, 120);
    }

    public static void main(String[] args) {
        GroupableHeaderExample frame = new GroupableHeaderExample();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}

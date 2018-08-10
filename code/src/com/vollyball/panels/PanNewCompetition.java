/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.CompetitionDao;
import com.vollyball.util.DateLabelFormatter;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanNewCompetition extends javax.swing.JPanel {

    UtilDateModel modelStart = new UtilDateModel();
    JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart);
    UtilDateModel modelEnd = new UtilDateModel();
    JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd);
    JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
    JDatePickerImpl datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());
    String ageGroup;
    CompetitionDao competitionDao = new CompetitionDao();
    int compId;

    /**
     * Creates new form PanNewCompetition
     */
    public PanNewCompetition() {
        initComponents();

        datePickerStart.setBounds(0, 0, 319, 28);
        datePickerStart.setBackground(Color.WHITE);
        panStartDate.add(datePickerStart);

        datePickerEnd.setBounds(0, 0, 319, 28);
        datePickerEnd.setBackground(Color.WHITE);
        panEndDate.add(datePickerEnd);

    }

    public PanNewCompetition(int compId) {
        initComponents();

        System.out.println("-------" + compId);
        CompetitionBean cb = competitionDao.getCompetitionById(compId);
        this.compId = cb.getId();

        datePickerStart.setBounds(0, 0, 319, 28);
        datePickerStart.setBackground(Color.WHITE);
        panStartDate.add(datePickerStart);

        datePickerEnd.setBounds(0, 0, 319, 28);
        datePickerEnd.setBackground(Color.WHITE);
        panEndDate.add(datePickerEnd);
        txtCompName.setText(cb.getName());
        txtVenue.setText(cb.getVenue());

        modelStart.setDate(2016, 00, 12);
        modelStart.setSelected(true);

        modelEnd.setDate(2016, 00, 12);
        modelEnd.setSelected(true);

        cmbAgeGroup.setSelectedItem(cb.getAgeGroup());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        txtCompName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtVenue = new javax.swing.JTextArea();
        panEndDate = new javax.swing.JPanel();
        panStartDate = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        cmbAgeGroup = new javax.swing.JComboBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCompName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCompName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCompName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompNameActionPerformed(evt);
            }
        });
        txtCompName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompNameKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblHeading.setBackground(new java.awt.Color(57, 74, 108));
        lblHeading.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("New Competition");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Venue");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Start Date");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("End Date");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Age group");

        txtVenue.setColumns(20);
        txtVenue.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtVenue.setRows(5);
        jScrollPane2.setViewportView(txtVenue);

        panEndDate.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panEndDateLayout = new javax.swing.GroupLayout(panEndDate);
        panEndDate.setLayout(panEndDateLayout);
        panEndDateLayout.setHorizontalGroup(
            panEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panEndDateLayout.setVerticalGroup(
            panEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        panStartDate.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panStartDateLayout = new javax.swing.GroupLayout(panStartDate);
        panStartDate.setLayout(panStartDateLayout);
        panStartDateLayout.setHorizontalGroup(
            panStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panStartDateLayout.setVerticalGroup(
            panStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SAVE");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(57, 74, 108));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("CANCEL");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Open", "Under" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        cmbAgeGroup.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbAgeGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "14", "16", "18", "21" }));
        cmbAgeGroup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAgeGroupItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(cmbAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtCompName)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(panEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCompName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();

            if (item.equals("Open")) {
                ageGroup = "Open";
                cmbAgeGroup.setVisible(false);
            } else if (item.equals("Under")) {
                ageGroup = "";
                cmbAgeGroup.setVisible(true);
            } else {
                ageGroup = "select";
                cmbAgeGroup.setVisible(false);
            }
            // do something with object
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void cmbAgeGroupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAgeGroupItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            ageGroup = "Under " + item;
            // do something with object
        }
    }//GEN-LAST:event_cmbAgeGroupItemStateChanged

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        String msg = validateFields();
        // TODO add your handling code here:
        if (msg.isEmpty()) {
            if (compId == 0) {
                Date selectedStartDate = (Date) datePickerStart.getModel().getValue();
                Date selectedEndDate = (Date) datePickerEnd.getModel().getValue();
                CompetitionBean cb = new CompetitionBean();
                cb.setName(txtCompName.getText());
                cb.setVenue(txtVenue.getText());
                cb.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedStartDate));
                cb.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedEndDate));
                cb.setAgeGroup(ageGroup);
                int count = competitionDao.insertCompetition(cb);

                if (count != 0) {

                    Controller.createCompetitionDialog.close();

                    Controller.panCompetitionList.refresh();
                    JOptionPane.showMessageDialog(this, "Inserted");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            } else {
                Date selectedStartDate = (Date) datePickerStart.getModel().getValue();
                Date selectedEndDate = (Date) datePickerEnd.getModel().getValue();
                CompetitionBean cb = new CompetitionBean();

                cb.setId(compId);
                cb.setName(txtCompName.getText());
                cb.setVenue(txtVenue.getText());
                cb.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedStartDate));
                cb.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedEndDate));
                cb.setAgeGroup(ageGroup);
                int count = competitionDao.updateCompetition(cb);

                if (count != 0) {

                    Controller.createCompetitionDialog.close();

                    Controller.panCompetitionList.refresh();
                    JOptionPane.showMessageDialog(this, "Updated");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }

            }
        } else {
            JOptionPane.showMessageDialog(this, msg);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    public String validateFields() {
        String msg = "";

        if (txtCompName.getText().equals("")) {
            msg = msg + "Compitition cannot be Blank\n";
        }

        if (txtVenue.getText().equals("")) {
            msg = msg + "Venue cannot be Blank\n";
        }

        if (datePickerStart.getModel().getValue() == null) {
            msg = msg + "Start Date cannot be Blank\n";
        }
        if (datePickerEnd.getModel().getValue() == null) {
            msg = msg + "End Date cannot be Blank\n";
        }

        if (ageGroup == null) {
            msg = msg + "Select Age Group\n";
        }

//        if (cmbAgeGroup.getSelectedItem().equals("Select")) {
//            msg = msg + "Select Age Group \n";
//        }
        return msg;

    }

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        Controller.createCompetitionDialog.close();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void txtCompNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompNameKeyReleased
        // TODO add your handling code here:

        lblHeading.setText(txtCompName.getText());
        if (txtCompName.getText().equals("")) {
            lblHeading.setText("New Competition");
        }
    }//GEN-LAST:event_txtCompNameKeyReleased

    private void txtCompNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompNameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbAgeGroup;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JPanel panEndDate;
    private javax.swing.JPanel panStartDate;
    private javax.swing.JTextField txtCompName;
    private javax.swing.JTextArea txtVenue;
    // End of variables declaration//GEN-END:variables
}

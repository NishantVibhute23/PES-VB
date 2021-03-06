/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.UserBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.LoginDao;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

/**
 *
 * @author #my
 */
public class PanUserProfile extends javax.swing.JPanel {

    LoginDao loginDao = new LoginDao();

    /**
     * Creates new form PanUserProfile
     */
    public PanUserProfile() {
        initComponents();
        lblUser.setText(" ");
        lblPassword.setText(" ");
        lblConfPassword.setText(" ");
        lblEmail.setText(" ");
//        txtPassword.setText(Controller.userBean.getEmailId());
        txtuserName.setText(Controller.userBean.getUserName());
        txtEmail.setText(Controller.userBean.getEmailId());
        txtPassword.setText(Controller.userBean.getPassword());
        txtConfPassword.setText(Controller.userBean.getPassword());
         txtEmail.setEditable(false);
        txtuserName.setEditable(false);
        txtPassword.setEditable(false);
        txtConfPassword.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtuserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtConfPassword = new javax.swing.JPasswordField();
        lblName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblConfPassword = new javax.swing.JLabel();
        lbledit = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        PlayerLabel2 = new javax.swing.JLabel();
        PlayerLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblHeading.setBackground(new java.awt.Color(57, 74, 108));
        lblHeading.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("Profile");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblHeading)
                .addGap(5, 5, 5))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Confirm Password");

        lblName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 0, 0));

        lblPassword.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblPassword.setText("lbl");

        lblConfPassword.setBackground(new java.awt.Color(255, 255, 255));
        lblConfPassword.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblConfPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblConfPassword.setText("lbl");

        lbledit.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbledit.setForeground(new java.awt.Color(0, 0, 255));
        lbledit.setText("[Edit]");
        lbledit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbledit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbleditMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        lblEmail.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 0, 0));
        lblEmail.setText("lbl");

        lblUser.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 0, 0));
        lblUser.setText("lbl");

        PlayerLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        PlayerLabel2.setForeground(new java.awt.Color(255, 255, 255));
        PlayerLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button_cancel.png"))); // NOI18N
        PlayerLabel2.setToolTipText("Cancel");
        PlayerLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PlayerLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerLabel2MouseClicked(evt);
            }
        });

        PlayerLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        PlayerLabel1.setForeground(new java.awt.Color(255, 255, 255));
        PlayerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button_save.png"))); // NOI18N
        PlayerLabel1.setToolTipText("Save");
        PlayerLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PlayerLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblConfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbledit))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                    .addComponent(txtConfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtuserName))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(PlayerLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PlayerLabel2)
                                .addGap(78, 78, 78))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbledit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtuserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblName)
                .addGap(3, 3, 3)
                .addComponent(lblUser)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblEmail)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblPassword)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lblConfPassword)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerLabel1)
                    .addComponent(PlayerLabel2))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PlayerLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerLabel1MouseClicked
        // TODO add your handling code here:
        int count = validateForm();
        if (count == 0) {
            UserBean ub = new UserBean();
            ub.setId(Controller.userBean.getId());
            ub.setUserName(txtuserName.getText());
            
            ub.setEmailId(txtEmail.getText());
            ub.setPassword(new String(txtPassword.getPassword()));
            int status = loginDao.updateUserPassword(ub);
            if (status != 0) {
                JOptionPane.showMessageDialog(this, "Profile Updated Successfully");
                 Controller.userBean = loginDao.getUserDetails(Controller.userBean.getId());
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update Profile");
            }
            Controller.createUserDialog.close();
        }
        
    }//GEN-LAST:event_PlayerLabel1MouseClicked

    private void lbleditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbleditMouseClicked
        // TODO add your handling code here:
        
                 txtEmail.setEditable(true);
        txtuserName.setEditable(true);
        txtPassword.setEditable(true);
        txtConfPassword.setEditable(true);

    }//GEN-LAST:event_lbleditMouseClicked

    private void PlayerLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerLabel2MouseClicked
        // TODO add your handling code here:
         Controller.createUserDialog.close();
        
        
    }//GEN-LAST:event_PlayerLabel2MouseClicked

    public int validateForm() {
        int count = 0;
        lblUser.setText(" ");
        lblPassword.setText(" ");
        lblConfPassword.setText(" ");
        lblEmail.setText(" ");
        if (txtuserName.getText().equals("")) {
            lblUser.setText("UserName cannot be Blank");
            count++;
        }

        if (txtEmail.getText().equals("")) {
            lblEmail.setText("E-Mail cannot be Blank");
            count++;
         
        }
        if (!txtEmail.getText().equals("")) {
            String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            Boolean b = txtEmail.getText().matches(EMAIL_REGEX);
            if (!b) {
                lblEmail.setText("E-Mail is Incorrect");
               
//            msg = msg + "E-Mail is Incorrect\n";
                count++;
            }

        }
        
        if (new String(txtPassword.getPassword()).equals("")) {
            lblPassword.setText("Password cannot be Blank");
            count++;
        }

        if (!new String(txtPassword.getPassword()).equals(new String(txtConfPassword.getPassword()))) {
            lblConfPassword.setText("Password Didn't match");
            count++;
        }

        return count;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerLabel1;
    private javax.swing.JLabel PlayerLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfPassword;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lbledit;
    private javax.swing.JPasswordField txtConfPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtuserName;
    // End of variables declaration//GEN-END:variables
}

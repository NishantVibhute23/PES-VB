/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.panel;

import com.vollyball.bean.Player;
import com.vollyball.bean.TeamRowBean;
import com.vollyball.controller.Controller;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author #dabbu
 */
public class PanNewBatch extends javax.swing.JPanel {

    List<Player> playerList;
    List<JComboBox> position = new ArrayList<>();
    List<JCheckBox> captainCheckBoxList = new ArrayList<>();

    List<TeamRowBean> teamRow = new ArrayList<>();
    List<JPanel> rowPanels = new ArrayList<>();
    List<JLabel> lblMinusList = new ArrayList<>();

    int i = 0, m = 6;

    String message;

    /**
     * Creates new form PanNewBatch
     */
    public PanNewBatch() {
        initComponents();
    }
    
       public String validate(JTextField jf) {
        String regexName = "^[a-zA-Z ]*$";
        String val = jf.getText();
        message = "";

        if (val.equalsIgnoreCase("")) {
            message = " cannot be Empty";

        } else if (!val.matches(regexName)) {
            message = " must contains only Letters";

        }
        return message;

    }

    public String validateNumber(JTextField jf) {
        String regexName = "^[0-9]*$";
        String val = jf.getText();
        message = "";

        if (val.equalsIgnoreCase("")) {
            message = " cannot be Empty";

        } else if (!val.matches(regexName)) {
            message = " must contains only Digits";

        }
        return message;

    }

    public void checkCharacter(java.awt.event.KeyEvent evt) {

        ((JTextField) evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        char c = evt.getKeyChar();
        if (!Character.isSpaceChar(c)) {
            if (!(Character.isAlphabetic(c))) {
                evt.consume();
            }
        }
    }

    public void checkNumber(java.awt.event.KeyEvent evt) {
        ((JTextField) evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c))) {
            evt.consume();
        }

    }

    public void setNumber(java.awt.event.FocusEvent evt) {
        JTextField jf = (JTextField) evt.getSource();
        String val = jf.getText();
        if (val.length() == 1) {
            jf.setText("0" + val);
        }
    }

    public String checkDuplicateChestNum() {
        String msg = "";
        List<String> chestNumList = new ArrayList<>();
        int k = 1;
        for (TeamRowBean tr : teamRow) {
            if (k <= m) {
                if (chestNumList.contains(tr.getChestnum().getText())) {
                    tr.getChestnum().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                    msg = "Chest Number must be unique";
                } else {
                    chestNumList.add(tr.getChestnum().getText());
                }

            }
            k++;
        }
        return msg;
    }

    public String validateFields() {
        String msg = "";
        boolean isCaptainSelected = false;
        int k = 1;

        if (!validate(txtTeamName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtTeamName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtShortCode).isEmpty()) {
            msg = msg + "ShortCode" + message + "\n";
            txtShortCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        

        for (TeamRowBean tr : teamRow) {
            if (k <= m) {
                if (!validate(tr.getName()).isEmpty()) {
                    msg = msg + "Player Name " + k + " :" + message + "\n";
                    tr.getName().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (!validateNumber(tr.getChestnum()).isEmpty()) {
                    msg = msg + "Chest Num " + k + " : " + message + "\n";
                    tr.getChestnum().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (tr.getPosition().getSelectedIndex() == 0) {
                    msg = msg + "Position " + k + " : Select position of player" + "\n";
                    tr.getPosition().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (!isCaptainSelected) {
                    if (tr.getCaptain().isSelected()) {
                        isCaptainSelected = true;
                    }
                }
            }
            k++;

        }

        if (!isCaptainSelected) {

            msg = msg + "Select Atleast One Captain";
        }
        return msg;

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
        jPanel2 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTeamName = new javax.swing.JTextField();
        txtAnalyzerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMedOffName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtrainerName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtShortCode = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panRowShow = new javax.swing.JPanel();
        lblSrNo1 = new javax.swing.JTextField();
        txtPlayerName1 = new javax.swing.JTextField();
        txtChest1 = new javax.swing.JTextField();
        lblSrNo2 = new javax.swing.JTextField();
        txtPlayerName2 = new javax.swing.JTextField();
        txtChest2 = new javax.swing.JTextField();
        txtPlayerName3 = new javax.swing.JTextField();
        txtChest3 = new javax.swing.JTextField();
        lblSrNo3 = new javax.swing.JTextField();
        txtPlayerName4 = new javax.swing.JTextField();
        txtChest4 = new javax.swing.JTextField();
        lblSrNo4 = new javax.swing.JTextField();
        txtPlayerName5 = new javax.swing.JTextField();
        txtChest5 = new javax.swing.JTextField();
        lblSrNo5 = new javax.swing.JTextField();
        txtPlayerName6 = new javax.swing.JTextField();
        txtChest6 = new javax.swing.JTextField();
        lblSrNo6 = new javax.swing.JTextField();
        trRow7 = new javax.swing.JPanel();
        lblSrNo7 = new javax.swing.JTextField();
        txtPlayerName7 = new javax.swing.JTextField();
        txtChest7 = new javax.swing.JTextField();
        lblSub = new javax.swing.JLabel();
        trRow8 = new javax.swing.JPanel();
        lblSrNo8 = new javax.swing.JTextField();
        txtPlayerName8 = new javax.swing.JTextField();
        txtChest8 = new javax.swing.JTextField();
        lblSub1 = new javax.swing.JLabel();
        trRow9 = new javax.swing.JPanel();
        txtChest9 = new javax.swing.JTextField();
        txtPlayerName9 = new javax.swing.JTextField();
        lblSrNo9 = new javax.swing.JTextField();
        lblSub2 = new javax.swing.JLabel();
        trRow10 = new javax.swing.JPanel();
        lblSrNo10 = new javax.swing.JTextField();
        txtPlayerName10 = new javax.swing.JTextField();
        txtChest10 = new javax.swing.JTextField();
        lblSub3 = new javax.swing.JLabel();
        trRow11 = new javax.swing.JPanel();
        lblSrNo11 = new javax.swing.JTextField();
        txtPlayerName11 = new javax.swing.JTextField();
        txtChest11 = new javax.swing.JTextField();
        lblSub4 = new javax.swing.JLabel();
        trRow12 = new javax.swing.JPanel();
        lblSrNo12 = new javax.swing.JTextField();
        txtPlayerName12 = new javax.swing.JTextField();
        txtChest12 = new javax.swing.JTextField();
        lblSub5 = new javax.swing.JLabel();
        trRow13 = new javax.swing.JPanel();
        lblSrNo13 = new javax.swing.JTextField();
        txtPlayerName13 = new javax.swing.JTextField();
        txtChest13 = new javax.swing.JTextField();
        lblSub6 = new javax.swing.JLabel();
        trRow14 = new javax.swing.JPanel();
        lblSrNo14 = new javax.swing.JTextField();
        txtPlayerName14 = new javax.swing.JTextField();
        txtChest14 = new javax.swing.JTextField();
        lblSub7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblCancel = new javax.swing.JLabel();
        panAddPlayer = new javax.swing.JPanel();
        lblAddNew = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));

        lblHeading.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("New Batch");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addGap(140, 140, 140))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblHeading)
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 62, 79));
        jLabel2.setText("Batch Name*");

        txtTeamName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTeamName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTeamName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeamNameKeyTyped(evt);
            }
        });

        txtAnalyzerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtAnalyzerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAnalyzerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnalyzerNameKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(45, 62, 79));
        jLabel6.setText("Medical Officer Name");

        txtMedOffName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMedOffName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMedOffName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedOffNameKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(45, 62, 79));
        jLabel7.setText("Analyzer  Name");

        txtrainerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtrainerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtrainerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrainerNameKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(45, 62, 79));
        jLabel18.setText("Trainer Name");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(45, 62, 79));
        jLabel20.setText("Short Code*");

        txtShortCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtShortCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtShortCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtShortCodeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(txtTeamName))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtShortCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtrainerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMedOffName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtShortCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedOffName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrainerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NAME *");
        jLabel8.setToolTipText("");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CHEST No. *");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SR No.");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panRowShow.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo1.setText("1");
        lblSrNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo1.setEnabled(false);
        lblSrNo1.setFocusable(false);

        txtPlayerName1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName1KeyTyped(evt);
            }
        });

        txtChest1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest1FocusLost(evt);
            }
        });
        txtChest1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest1KeyTyped(evt);
            }
        });

        lblSrNo2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo2.setText("2");
        lblSrNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo2.setEnabled(false);
        lblSrNo2.setFocusable(false);

        txtPlayerName2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName2KeyTyped(evt);
            }
        });

        txtChest2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest2FocusLost(evt);
            }
        });
        txtChest2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest2KeyTyped(evt);
            }
        });

        txtPlayerName3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName3KeyTyped(evt);
            }
        });

        txtChest3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest3FocusLost(evt);
            }
        });
        txtChest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChest3ActionPerformed(evt);
            }
        });
        txtChest3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest3KeyTyped(evt);
            }
        });

        lblSrNo3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo3.setText("3");
        lblSrNo3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo3.setEnabled(false);
        lblSrNo3.setFocusable(false);

        txtPlayerName4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName4KeyTyped(evt);
            }
        });

        txtChest4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest4FocusLost(evt);
            }
        });
        txtChest4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest4KeyTyped(evt);
            }
        });

        lblSrNo4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo4.setText("4");
        lblSrNo4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo4.setEnabled(false);
        lblSrNo4.setFocusable(false);

        txtPlayerName5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName5KeyTyped(evt);
            }
        });

        txtChest5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest5FocusLost(evt);
            }
        });
        txtChest5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest5KeyTyped(evt);
            }
        });

        lblSrNo5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo5.setText("5");
        lblSrNo5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo5.setEnabled(false);
        lblSrNo5.setFocusable(false);

        txtPlayerName6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName6KeyTyped(evt);
            }
        });

        txtChest6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest6FocusLost(evt);
            }
        });
        txtChest6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest6KeyTyped(evt);
            }
        });

        lblSrNo6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo6.setText("6");
        lblSrNo6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo6.setEnabled(false);
        lblSrNo6.setFocusable(false);

        trRow7.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo7.setText("7");
        lblSrNo7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo7.setEnabled(false);
        lblSrNo7.setFocusable(false);

        txtPlayerName7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName7KeyTyped(evt);
            }
        });

        txtChest7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest7FocusLost(evt);
            }
        });
        txtChest7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest7KeyTyped(evt);
            }
        });

        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub.setToolTipText("Remove Row");
        lblSub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow7Layout = new javax.swing.GroupLayout(trRow7);
        trRow7.setLayout(trRow7Layout);
        trRow7Layout.setHorizontalGroup(
            trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trRow7Layout.setVerticalGroup(
            trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow7Layout.createSequentialGroup()
                .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow8.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo8.setText("8");
        lblSrNo8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo8.setEnabled(false);
        lblSrNo8.setFocusable(false);

        txtPlayerName8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName8KeyTyped(evt);
            }
        });

        txtChest8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest8FocusLost(evt);
            }
        });
        txtChest8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest8KeyTyped(evt);
            }
        });

        lblSub1.setBackground(new java.awt.Color(255, 255, 255));
        lblSub1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub1.setToolTipText("Remove Row");
        lblSub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow8Layout = new javax.swing.GroupLayout(trRow8);
        trRow8.setLayout(trRow8Layout);
        trRow8Layout.setHorizontalGroup(
            trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trRow8Layout.setVerticalGroup(
            trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow8Layout.createSequentialGroup()
                .addGroup(trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow9.setBackground(new java.awt.Color(255, 255, 255));

        txtChest9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest9FocusLost(evt);
            }
        });
        txtChest9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest9KeyTyped(evt);
            }
        });

        txtPlayerName9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName9KeyTyped(evt);
            }
        });

        lblSrNo9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo9.setText("9");
        lblSrNo9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo9.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo9.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo9.setEnabled(false);
        lblSrNo9.setFocusable(false);

        lblSub2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub2.setToolTipText("Remove Row");
        lblSub2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow9Layout = new javax.swing.GroupLayout(trRow9);
        trRow9.setLayout(trRow9Layout);
        trRow9Layout.setHorizontalGroup(
            trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow9Layout.setVerticalGroup(
            trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow9Layout.createSequentialGroup()
                        .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSrNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChest9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        trRow10.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo10.setText("10");
        lblSrNo10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo10.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo10.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo10.setEnabled(false);
        lblSrNo10.setFocusable(false);

        txtPlayerName10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName10KeyTyped(evt);
            }
        });

        txtChest10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest10FocusLost(evt);
            }
        });
        txtChest10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest10KeyTyped(evt);
            }
        });

        lblSub3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub3.setToolTipText("Remove Row");
        lblSub3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow10Layout = new javax.swing.GroupLayout(trRow10);
        trRow10.setLayout(trRow10Layout);
        trRow10Layout.setHorizontalGroup(
            trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow10Layout.setVerticalGroup(
            trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSrNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtChest10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        trRow11.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo11.setText("11");
        lblSrNo11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo11.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo11.setEnabled(false);
        lblSrNo11.setFocusable(false);

        txtPlayerName11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName11KeyTyped(evt);
            }
        });

        txtChest11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest11FocusLost(evt);
            }
        });
        txtChest11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest11KeyTyped(evt);
            }
        });

        lblSub4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub4.setToolTipText("Remove Row");
        lblSub4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow11Layout = new javax.swing.GroupLayout(trRow11);
        trRow11.setLayout(trRow11Layout);
        trRow11Layout.setHorizontalGroup(
            trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow11Layout.setVerticalGroup(
            trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow11Layout.createSequentialGroup()
                .addGroup(trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow12.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo12.setText("12");
        lblSrNo12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo12.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo12.setEnabled(false);
        lblSrNo12.setFocusable(false);

        txtPlayerName12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName12KeyTyped(evt);
            }
        });

        txtChest12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest12FocusLost(evt);
            }
        });
        txtChest12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest12KeyTyped(evt);
            }
        });

        lblSub5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub5.setToolTipText("Remove Row");
        lblSub5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow12Layout = new javax.swing.GroupLayout(trRow12);
        trRow12.setLayout(trRow12Layout);
        trRow12Layout.setHorizontalGroup(
            trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow12Layout.setVerticalGroup(
            trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSrNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtChest12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        trRow13.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo13.setText("13");
        lblSrNo13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo13.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo13.setEnabled(false);
        lblSrNo13.setFocusable(false);

        txtPlayerName13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName13KeyTyped(evt);
            }
        });

        txtChest13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest13FocusLost(evt);
            }
        });
        txtChest13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest13KeyTyped(evt);
            }
        });

        lblSub6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub6.setToolTipText("Remove Row");
        lblSub6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow13Layout = new javax.swing.GroupLayout(trRow13);
        trRow13.setLayout(trRow13Layout);
        trRow13Layout.setHorizontalGroup(
            trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        trRow13Layout.setVerticalGroup(
            trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow13Layout.createSequentialGroup()
                        .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSrNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChest13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        trRow14.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo14.setText("14");
        lblSrNo14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo14.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo14.setEnabled(false);
        lblSrNo14.setFocusable(false);

        txtPlayerName14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName14KeyTyped(evt);
            }
        });

        txtChest14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest14FocusLost(evt);
            }
        });
        txtChest14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest14KeyTyped(evt);
            }
        });

        lblSub7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub7.setToolTipText("Remove Row");
        lblSub7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow14Layout = new javax.swing.GroupLayout(trRow14);
        trRow14.setLayout(trRow14Layout);
        trRow14Layout.setHorizontalGroup(
            trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow14Layout.createSequentialGroup()
                .addComponent(lblSrNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChest14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblSub7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        trRow14Layout.setVerticalGroup(
            trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow14Layout.createSequentialGroup()
                .addGroup(trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panRowShowLayout = new javax.swing.GroupLayout(panRowShow);
        panRowShow.setLayout(panRowShowLayout);
        panRowShowLayout.setHorizontalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trRow13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(trRow14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(trRow8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                        .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtChest1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(txtChest2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(txtChest3))
                                    .addComponent(txtChest5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtChest4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtChest6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(trRow9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panRowShowLayout.setVerticalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(trRow7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panRowShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRowShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("TRAINEES");

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("* Mandatory Fields");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(57, 74, 108));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSave.setForeground(new java.awt.Color(255, 255, 255));
        lblSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSave.setText("SAVE");
        lblSave.setToolTipText("");
        lblSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSave, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(57, 74, 108));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCancel.setBackground(new java.awt.Color(57, 74, 108));
        lblCancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCancel.setForeground(new java.awt.Color(255, 255, 255));
        lblCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCancel.setText("CANCEL");
        lblCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        panAddPlayer.setBackground(new java.awt.Color(102, 102, 102));
        panAddPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddNew.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAddNew.setForeground(new java.awt.Color(255, 255, 255));
        lblAddNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddNew.setText("New Player");
        lblAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAddPlayerLayout = new javax.swing.GroupLayout(panAddPlayer);
        panAddPlayer.setLayout(panAddPlayerLayout);
        panAddPlayerLayout.setHorizontalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        panAddPlayerLayout.setVerticalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTeamNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeamNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtTeamNameKeyTyped

    private void txtAnalyzerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnalyzerNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtAnalyzerNameKeyTyped

    private void txtMedOffNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedOffNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtMedOffNameKeyTyped

    private void txtrainerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrainerNameKeyTyped

        checkCharacter(evt);
    }//GEN-LAST:event_txtrainerNameKeyTyped

    private void txtShortCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShortCodeKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtShortCodeKeyTyped

    private void txtPlayerName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName1KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName1KeyTyped

    private void txtChest1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest1FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest1FocusLost

    private void txtChest1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest1KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest1KeyTyped

    private void txtPlayerName2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName2KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName2KeyTyped

    private void txtChest2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest2FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest2FocusLost

    private void txtChest2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest2KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest2KeyTyped

    private void txtPlayerName3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName3KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName3KeyTyped

    private void txtChest3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest3FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest3FocusLost

    private void txtChest3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest3KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest3KeyTyped

    private void txtPlayerName4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName4KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName4KeyTyped

    private void txtChest4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest4FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest4FocusLost

    private void txtChest4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest4KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest4KeyTyped

    private void txtPlayerName5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName5KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName5KeyTyped

    private void txtChest5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest5FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest5FocusLost

    private void txtChest5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest5KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest5KeyTyped

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        hidePanel(1);
    }//GEN-LAST:event_lblSub1MouseClicked

    private void lblSub2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub2MouseClicked
        // TODO add your handling code here:
        hidePanel(2);
    }//GEN-LAST:event_lblSub2MouseClicked

    private void lblSub3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub3MouseClicked
        // TODO add your handling code here:
        hidePanel(3);
    }//GEN-LAST:event_lblSub3MouseClicked

    private void lblSub4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub4MouseClicked
        // TODO add your handling code here:
        hidePanel(4);
    }//GEN-LAST:event_lblSub4MouseClicked

    private void lblSub6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub6MouseClicked
        // TODO add your handling code here:
        hidePanel(6);
    }//GEN-LAST:event_lblSub6MouseClicked

    private void lblSub7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub7MouseClicked
        // TODO add your handling code here:
        hidePanel(7);
    }//GEN-LAST:event_lblSub7MouseClicked

    private void lblSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaveMouseClicked

       
    }//GEN-LAST:event_lblSaveMouseClicked

    private void lblCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelMouseClicked
        Controller.teamDialog.close();
    }//GEN-LAST:event_lblCancelMouseClicked

    private void lblAddNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddNewMouseClicked
        // TODO add your handling code here:
        if (i != 0) {
            lblMinusList.get(i - 1).setVisible(false);
        }
        rowPanels.get(i).setVisible(true);
        i++;
        m++;
        if (i == 8) {
            panAddPlayer.setVisible(false);
        }
    }//GEN-LAST:event_lblAddNewMouseClicked

    private void txtChest3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChest3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChest3ActionPerformed

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        hidePanel(0);
    }//GEN-LAST:event_lblSubMouseClicked

    private void txtChest6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest6KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest6KeyTyped

    private void txtChest6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest6FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest6FocusLost

    private void txtChest7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest7KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest7KeyTyped

    private void txtChest7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest7FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest7FocusLost

    private void txtChest8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest8KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest8KeyTyped

    private void txtChest8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest8FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest8FocusLost

    private void txtChest9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest9KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest9KeyTyped

    private void txtChest9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest9FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest9FocusLost

    private void txtChest10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest10KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest10KeyTyped

    private void txtChest10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest10FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest10FocusLost

    private void txtChest11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest11KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest11KeyTyped

    private void txtChest11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest11FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest11FocusLost

    private void txtChest13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest13KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest13KeyTyped

    private void txtChest13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest13FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest13FocusLost

    private void txtChest14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest14KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest14KeyTyped

    private void txtChest14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest14FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest14FocusLost

    private void txtPlayerName14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName14KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName14KeyTyped

    private void txtPlayerName13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName13KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName13KeyTyped

    private void txtPlayerName11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName11KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName11KeyTyped

    private void txtPlayerName10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName10KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName10KeyTyped

    private void txtPlayerName9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName9KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName9KeyTyped

    private void txtPlayerName8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName8KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName8KeyTyped

    private void txtPlayerName7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName7KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName7KeyTyped

    private void txtPlayerName6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName6KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName6KeyTyped

    private void lblSub5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub5MouseClicked
        // TODO add your handling code here:
        hidePanel(5);
    }//GEN-LAST:event_lblSub5MouseClicked

    private void txtChest12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest12KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest12KeyTyped

    private void txtChest12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest12FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest12FocusLost

    private void txtPlayerName12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName12KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName12KeyTyped

    
     public void hidePanel(int j) {
        if (j != 0) {
            lblMinusList.get(j - 1).setVisible(true);
        }
        panAddPlayer.setVisible(true);
        rowPanels.get(j).setVisible(false);
        i--;
        m--;
    }

    public void enabledDisabledCheckBox(java.awt.event.ItemEvent evt) {
        String type = "";
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            type = "checked";
        } else {
            type = "unchecked";
        }

        if (type.equals("checked")) {
            for (JCheckBox chk : captainCheckBoxList) {
                if (chk != checkBox) {
                    chk.setEnabled(false);
                }
            }
        } else {
            for (JCheckBox chk : captainCheckBoxList) {
                if (chk != checkBox) {
                    chk.setEnabled(true);
                }
            }
        }
    }

    public void addPlayer(String name, String chestNum, int position, boolean isCaptain) {
        Player p = new Player();
        p.setName(name);
        p.setChestNo(chestNum);
        p.setPosition(position);
        p.setCaptain(isCaptain);
        playerList.add(p);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAddNew;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblSave;
    private javax.swing.JTextField lblSrNo1;
    private javax.swing.JTextField lblSrNo10;
    private javax.swing.JTextField lblSrNo11;
    private javax.swing.JTextField lblSrNo12;
    private javax.swing.JTextField lblSrNo13;
    private javax.swing.JTextField lblSrNo14;
    private javax.swing.JTextField lblSrNo2;
    private javax.swing.JTextField lblSrNo3;
    private javax.swing.JTextField lblSrNo4;
    private javax.swing.JTextField lblSrNo5;
    private javax.swing.JTextField lblSrNo6;
    private javax.swing.JTextField lblSrNo7;
    private javax.swing.JTextField lblSrNo8;
    private javax.swing.JTextField lblSrNo9;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSub1;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblSub3;
    private javax.swing.JLabel lblSub4;
    private javax.swing.JLabel lblSub5;
    private javax.swing.JLabel lblSub6;
    private javax.swing.JLabel lblSub7;
    private javax.swing.JPanel panAddPlayer;
    private javax.swing.JPanel panRowShow;
    private javax.swing.JPanel trRow10;
    private javax.swing.JPanel trRow11;
    private javax.swing.JPanel trRow12;
    private javax.swing.JPanel trRow13;
    private javax.swing.JPanel trRow14;
    private javax.swing.JPanel trRow7;
    private javax.swing.JPanel trRow8;
    private javax.swing.JPanel trRow9;
    private javax.swing.JTextField txtAnalyzerName;
    private javax.swing.JTextField txtChest1;
    private javax.swing.JTextField txtChest10;
    private javax.swing.JTextField txtChest11;
    private javax.swing.JTextField txtChest12;
    private javax.swing.JTextField txtChest13;
    private javax.swing.JTextField txtChest14;
    private javax.swing.JTextField txtChest2;
    private javax.swing.JTextField txtChest3;
    private javax.swing.JTextField txtChest4;
    private javax.swing.JTextField txtChest5;
    private javax.swing.JTextField txtChest6;
    private javax.swing.JTextField txtChest7;
    private javax.swing.JTextField txtChest8;
    private javax.swing.JTextField txtChest9;
    private javax.swing.JTextField txtMedOffName;
    private javax.swing.JTextField txtPlayerName1;
    private javax.swing.JTextField txtPlayerName10;
    private javax.swing.JTextField txtPlayerName11;
    private javax.swing.JTextField txtPlayerName12;
    private javax.swing.JTextField txtPlayerName13;
    private javax.swing.JTextField txtPlayerName14;
    private javax.swing.JTextField txtPlayerName2;
    private javax.swing.JTextField txtPlayerName3;
    private javax.swing.JTextField txtPlayerName4;
    private javax.swing.JTextField txtPlayerName5;
    private javax.swing.JTextField txtPlayerName6;
    private javax.swing.JTextField txtPlayerName7;
    private javax.swing.JTextField txtPlayerName8;
    private javax.swing.JTextField txtPlayerName9;
    private javax.swing.JTextField txtShortCode;
    private javax.swing.JTextField txtTeamName;
    private javax.swing.JTextField txtrainerName;
    // End of variables declaration//GEN-END:variables
}

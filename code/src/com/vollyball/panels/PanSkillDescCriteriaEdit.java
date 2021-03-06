/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Settings;
import com.vollyball.dao.SettingDao;
import com.vollyball.enums.SkillDescCriteriaPoint;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author nishant.vibhute
 */
public class PanSkillDescCriteriaEdit extends javax.swing.JPanel {

    int id;
    LinkedHashMap<String, Integer> pointsMap = new LinkedHashMap<>();
    public JTextField txtVal;
    public JComboBox cmbVal;
    String value="";
    SettingDao settingDao = new SettingDao();
    LinkedHashMap<String, Integer> shorcutId = new LinkedHashMap<>();
    String code = "";
    
    /**
     * Creates new form PanSkillDescCriteria
     */
    public PanSkillDescCriteriaEdit(int idm) {
        initComponents();
        this.id = idm;
        if(idm==0)
        {
                              txtVal=new JTextField();
           
            txtVal.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                       
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    value = txtVal.getText().toUpperCase();
                       code= value;
                }
            });
            
            panSelect.add(txtVal,BorderLayout.CENTER);
        }
        
      
        else
        {
        List<SkillDescCriteriaPoint> lstPoints = SkillDescCriteriaPoint.getTypeBySkillDescId(id);
            cmbVal = new JComboBox();
            cmbVal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == ItemEvent.SELECTED) {
                   value = (String) cmbVal.getSelectedItem();
                   if(value.equalsIgnoreCase("Select"))
                   {
                       value="";
                       code="";
                   }else{
                     int id=  shorcutId.get(value);
                     
                     if(id!=0)
                     {
                     Settings s= settingDao.getCodeForId(id);
                     code = s.getCode();
                     }                   
                   }
                }
            }
        });
            
            
            panSelect.add(cmbVal,BorderLayout.CENTER);
            
            cmbVal .addItem("Select");
            for (SkillDescCriteriaPoint val : lstPoints) {
                shorcutId.put(val.getAbbreviation(), val.getShortcutId());
                cmbVal.addItem(val.getAbbreviation());
            }
        }
    }
    
    public void setValue(String val)
    {
        if(id==0)
        {
            txtVal.setText(val);
             value = txtVal.getText().toUpperCase();
                       code= value;
          
        }else{
            cmbVal.setSelectedItem(val);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedHashMap<String, Integer> getPointsMap() {
        return pointsMap;
    }

    public void setPointsMap(LinkedHashMap<String, Integer> pointsMap) {
        this.pointsMap = pointsMap;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeading = new javax.swing.JLabel();
        panSelect = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panSelect.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lblHeading;
    private javax.swing.JPanel panSelect;
    // End of variables declaration//GEN-END:variables
}

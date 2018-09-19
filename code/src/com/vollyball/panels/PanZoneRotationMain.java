/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerPositionBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author nishant.vibhute
 */
public class PanZoneRotationMain extends javax.swing.JPanel {

    public PanRotationListValue panRotationListValue;
    public LinkedHashMap<Integer, Player> playerMapOpp = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMapOpp = new LinkedHashMap<String, Player>();
    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<String, Player>();
    List<Player> playerList, playerListOpp;
    TeamDao teamDao = new TeamDao();
    int evaluationteamIdHome, evaluationteamIdOpp;
    int team1id, team2id;
    ReportDao reportDao = new ReportDao();
    MatchDao matchDao = new MatchDao();
    int[] positions = {1, 6, 5, 4, 3, 2};
    String team1Name = "", team2Name = "";

    /**
     * Creates new form PanZoneRotationMain
     */
    public PanZoneRotationMain(int cb, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        team1Name = team.getTeam1name();
        team2Name = team.getTeam2name();
        evaluationteamIdHome = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamIdOpp = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        playerList = teamDao.getTeamPlayers(team1id);
        playerListOpp = teamDao.getTeamPlayers(team2id);

        for (Player p : playerList) {
            playerMap.put(p.getId(), p);
            ChestMap.put(p.getChestNo(), p);
        }

        for (Player p : playerListOpp) {
            playerMapOpp.put(p.getId(), p);
            ChestMapOpp.put(p.getChestNo(), p);
        }
        panRotatonRows.add(new PanRotationListValue(), BorderLayout.CENTER);
    }

    public class PanRotationListValue extends JPanel {

        private JPanel mainList;

        public PanRotationListValue() {
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            add(new JScrollPane(mainList));
            int k = -1;
            for (int i = 1; i <= 5; i++) {

                LinkedHashMap<Integer, PlayerPositionBean> rallyRotationMapOpp = new LinkedHashMap<>();
                LinkedHashMap<Integer, PlayerPositionBean> rallyRotationMapHome = new LinkedHashMap<>();

                rallyRotationMapHome.putAll(setMap(evaluationteamIdHome, i, playerMap));
                rallyRotationMapOpp.putAll(setMap(evaluationteamIdOpp, i, playerMapOpp));

                if (!rallyRotationMapHome.isEmpty() || !rallyRotationMapOpp.isEmpty()) {

                    GridBagConstraints gbcRow = new GridBagConstraints();
                    gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                    gbcRow.weightx = 1;
                    gbcRow.gridheight = 2;
                    gbcRow.fill = GridBagConstraints.HORIZONTAL;
                    JLabel lblSet = new JLabel("SET " + i);
                    lblSet.setPreferredSize(new Dimension(996, 30));
                    lblSet.setFont(new Font("Times New Roman", Font.BOLD, 12));
                    mainList.add(lblSet, gbcRow, ++k);

                    if (!rallyRotationMapHome.isEmpty()) {
                        JLabel lblHome = new JLabel(team1Name);
                        lblHome.setPreferredSize(new Dimension(996, 30));
                        lblHome.setFont(new Font("Times New Roman", Font.BOLD, 12));
                        mainList.add(lblHome, gbcRow, ++k);

                        JPanel panelHome = new JPanel();
                        panelHome.setLayout(new GridLayout(1, 6));
                        panelHome.setBackground(Color.WHITE);

                        int homeRot = 0;
                        for (int pos : positions) {

                            if (rallyRotationMapHome.get(pos) != null) {
                                homeRot++;
                                PanZoneRotationBlock pan = new PanZoneRotationBlock(rallyRotationMapHome.get(pos), homeRot);
                                panelHome.add(pan);
                            }
                        }
                        for (int q = homeRot + 1; q <= 6; q++) {
                            JPanel pan = new JPanel();
//                            pan.setLayout(new BorderLayout());
//                            JLabel l = new JLabel("this");
//                            pan.add(l, BorderLayout.CENTER);

                            pan.setPreferredSize(new Dimension(166, 208));
//                            pan.setBackground(Color.red);
                            panelHome.add(pan);
                        }
                        mainList.add(panelHome, gbcRow, ++k);
                    }

                    if (!rallyRotationMapOpp.isEmpty()) {
                        JLabel lblOpp = new JLabel(team2Name);
                        lblOpp.setPreferredSize(new Dimension(996, 30));
                        lblOpp.setFont(new Font("Times New Roman", Font.BOLD, 12));
                        mainList.add(lblOpp, gbcRow, ++k);

                        JPanel panelOpp = new JPanel();
                        panelOpp.setLayout(new GridLayout(1, 6));
                        panelOpp.setBackground(Color.WHITE);
                        int oppRot = 0;
                        for (int pos : positions) {

                            if (rallyRotationMapOpp.get(pos) != null) {
                                oppRot++;
                                PanZoneRotationBlock pan = new PanZoneRotationBlock(rallyRotationMapOpp.get(pos), oppRot);
                                panelOpp.add(pan);
                            }
                        }

                        for (int q = oppRot + 1; q <= 6; q++) {
                            JPanel pan = new JPanel();
                            pan.setPreferredSize(new Dimension(166, 208));
                            panelOpp.add(pan);
                        }
                        mainList.add(panelOpp, gbcRow, ++k);
                    }
                    JPanel panBlank = new JPanel();
                    panBlank.setPreferredSize(new Dimension(640, 10));
                    mainList.add(panBlank, gbcRow, ++k);
                    JPanel panHr = new JPanel();
                    panHr.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    panHr.setPreferredSize(new Dimension(640, 2));
                    mainList.add(panHr, gbcRow, ++k);
                    panBlank = new JPanel();
                    panBlank.setPreferredSize(new Dimension(640, 10));
                    mainList.add(panBlank, gbcRow, ++k);
                }
            }
        }
    }

    public LinkedHashMap<Integer, PlayerPositionBean> setMap(int evaluationId, int setNum, LinkedHashMap<Integer, Player> playerMap) {
        List<PlayerPositionBean> list = reportDao.getRotationOrders(evaluationId, setNum, playerMap);
        LinkedHashMap<Integer, PlayerPositionBean> rallyRotationMap = new LinkedHashMap<>();

        for (PlayerPositionBean p : list) {
            int pos = 1;
            if (p.pos.get(1).getPosition() == 6) {
                pos = 1;
            } else if (p.pos.get(2).getPosition() == 6) {
                pos = 2;
            } else if (p.pos.get(3).getPosition() == 6) {
                pos = 3;
            } else if (p.pos.get(4).getPosition() == 6) {
                pos = 4;
            } else if (p.pos.get(5).getPosition() == 6) {
                pos = 5;
            } else if (p.pos.get(6).getPosition() == 6) {
                pos = 6;
            }

            PlayerPositionBean pb = rallyRotationMap.get(pos);

            if (pb == null) {
                p.setRotationCount(1);
                if (p.getWonby() == 1) {
                    p.setTotalSuccess(p.getTotalSuccess() + 1);
                } else {
                    p.setTotalLoss(p.getTotalLoss() + 1);
                }
                rallyRotationMap.put(pos, p);
            } else {
                if (p.getWonby() == 1) {
                    pb.setTotalSuccess(pb.getTotalSuccess() + 1);
                } else {
                    pb.setTotalLoss(pb.getTotalLoss() + 1);
                }
                pb.setRotationCount(pb.getRotationCount() + 1);
            }

        }

        for (int pos : positions) {
            PlayerPositionBean pb = rallyRotationMap.get(pos);

            if (pb != null) {
                int success = (int) (Math.round((double) pb.getTotalSuccess() / pb.getRotationCount() * 100));
                int loss = (int) (Math.round((double) pb.getTotalLoss() / pb.getRotationCount() * 100));
                pb.setSuccessPerc(success);
                pb.setLossPerc(loss);
            }
        }

        return rallyRotationMap;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        panRotatonRows = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        panRotatonRows.setBackground(new java.awt.Color(255, 255, 255));
        panRotatonRows.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Team", "Strongest", "Weakest", "Favourable", "Non Favourable"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panRotatonRows, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRotatonRows, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panRotatonRows;
    // End of variables declaration//GEN-END:variables
}

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
import com.vollyball.enums.HomeOpponent;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nishant.vibhute
 */
public class PanZoneRotationMain extends javax.swing.JPanel {

//    public PanRotationListValue panRotationListValue;
    public LinkedHashMap<Integer, Player> playerMapOpp = new LinkedHashMap<>();
    public LinkedHashMap<String, Player> ChestMapOpp = new LinkedHashMap<>();
    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<>();
    List<Player> playerList, playerListOpp;
    TeamDao teamDao = new TeamDao();
    int evaluationteamIdHome, evaluationteamIdOpp;
    int team1id, team2id;
    ReportDao reportDao = new ReportDao();
    MatchDao matchDao = new MatchDao();
    int[] positions = {1, 6, 5, 4, 3, 2};
    String team1Name = "", team2Name = "";

    TreeMap<Integer, String> homeSuccessful = new TreeMap<>(Collections.reverseOrder());
    TreeMap<Integer, String> oppSuccessful = new TreeMap<>(Collections.reverseOrder());
    TreeMap<Integer, String> homeWeakest = new TreeMap<>(Collections.reverseOrder());
    TreeMap<Integer, String> oppWeakest = new TreeMap<>(Collections.reverseOrder());
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    List<JPanel> panelHome = new ArrayList<>();
    List<JPanel> panelOpp = new ArrayList<>();

    /**
     * Creates new form PanZoneRotationMain
     *
     * @param cb
     * @param matchId
     */
    public PanZoneRotationMain(int cb, int matchId) {
        initComponents();
        JTableHeader header = tbOverall.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbOverall));
        tbOverall.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        team1Name = team.getTeam1name();
        team2Name = team.getTeam2name();
        evaluationteamIdHome = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamIdOpp = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        playerList = teamDao.getTeamPlayers(team1id);
        playerListOpp = teamDao.getTeamPlayers(team2id);

        panelHome.add(panHome1);
        panelHome.add(panHome2);
        panelHome.add(panHome3);
        panelHome.add(panHome4);
        panelHome.add(panHome5);

        panelOpp.add(panOpp1);
        panelOpp.add(panOpp2);
        panelOpp.add(panOpp3);
        panelOpp.add(panOpp4);
        panelOpp.add(panOpp5);

        for (Player p : playerList) {
            playerMap.put(p.getId(), p);
            ChestMap.put(p.getChestNo(), p);
        }

        for (Player p : playerListOpp) {
            playerMapOpp.put(p.getId(), p);
            ChestMapOpp.put(p.getChestNo(), p);
        }
        panRotatonRows.add(new PanRotationListValue(), BorderLayout.CENTER);

        lblHome1.setText(team1Name);
        lblOpp1.setText(team2Name);
        lblHome2.setText(team1Name);
        lblOpp2.setText(team2Name);
        lblHome3.setText(team1Name);
        lblOpp3.setText(team2Name);
        lblHome4.setText(team1Name);
        lblOpp4.setText(team2Name);
        lblHome5.setText(team1Name);
        lblOpp5.setText(team2Name);

        for (int i = 0; i <= 4; i++) {
            LinkedHashMap<Integer, PlayerPositionBean> rallyRotationMapOpp = new LinkedHashMap<>();
            LinkedHashMap<Integer, PlayerPositionBean> rallyRotationMapHome = new LinkedHashMap<>();
            rallyRotationMapHome.putAll(setMap(evaluationteamIdHome, i + 1, playerMap, HomeOpponent.HOME.getId()));
            rallyRotationMapOpp.putAll(setMap(evaluationteamIdOpp, i + 1, playerMapOpp, HomeOpponent.OPPONENT.getId()));

            if (!rallyRotationMapHome.isEmpty() || !rallyRotationMapOpp.isEmpty()) {

                panelHome.get(i).setLayout(new GridLayout(1, 6));
                panelHome.get(i).setBackground(Color.WHITE);

                int homeRot = 0;
                for (int pos : positions) {

                    if (rallyRotationMapHome.get(pos) != null) {
                        homeRot++;
                        PanZoneRotationBlock pan = new PanZoneRotationBlock(rallyRotationMapHome.get(pos), homeRot);
                        panelHome.get(i).add(pan);
                    }
                }
                for (int q = homeRot + 1; q <= 6; q++) {
                    JPanel pan = new JPanel();
                    pan.setPreferredSize(new Dimension(330, 172));
                    panelHome.get(i).add(pan);
                }

            }

            if (!rallyRotationMapOpp.isEmpty()) {
                panelOpp.get(i).setLayout(new GridLayout(1, 6));
                panelOpp.get(i).setBackground(Color.WHITE);
                int oppRot = 0;
                for (int pos : positions) {

                    if (rallyRotationMapOpp.get(pos) != null) {
                        oppRot++;
                        PanZoneRotationBlock pan = new PanZoneRotationBlock(rallyRotationMapOpp.get(pos), oppRot);
                        panelOpp.get(i).add(pan);
                    }
                }

                for (int q = oppRot + 1; q <= 6; q++) {
                    JPanel pan = new JPanel();
                    pan.setPreferredSize(new Dimension(330, 172));
                    panelOpp.get(i).add(pan);
                }
            }
        }

        DefaultTableModel model = (DefaultTableModel) tbOverall.getModel();
        model.addRow(new Object[]{team1Name, homeSuccessful.values().toArray().length > 1 ? homeSuccessful.values().toArray()[0] : "", homeWeakest.values().toArray().length > 1 ? homeWeakest.values().toArray()[0] : "", homeSuccessful.values().toArray().length > 2 ? homeSuccessful.values().toArray()[1] : "", homeWeakest.values().toArray().length > 1 ? homeWeakest.values().toArray()[1] : ""});
        model.addRow(new Object[]{team2Name, oppSuccessful.values().toArray().length > 1 ? oppSuccessful.values().toArray()[0] : "", oppWeakest.values().toArray().length > 1 ? oppWeakest.values().toArray()[0] : "", oppSuccessful.values().toArray().length > 2 ? oppSuccessful.values().toArray()[1] : "", oppWeakest.values().toArray().length > 1 ? oppWeakest.values().toArray()[1] : ""});

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

                rallyRotationMapHome.putAll(setMap(evaluationteamIdHome, i, playerMap, HomeOpponent.HOME.getId()));
                rallyRotationMapOpp.putAll(setMap(evaluationteamIdOpp, i, playerMapOpp, HomeOpponent.OPPONENT.getId()));

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

    public LinkedHashMap<Integer, PlayerPositionBean> setMap(int evaluationId, int setNum, LinkedHashMap<Integer, Player> playerMap, int team) {
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
        int i = 0;
        for (int pos : positions) {
            PlayerPositionBean pb = rallyRotationMap.get(pos);
            if (pb != null) {
                i++;
                int success = (int) (Math.round((double) pb.getTotalSuccess() / pb.getRotationCount() * 100));
                int loss = (int) (Math.round((double) pb.getTotalLoss() / pb.getRotationCount() * 100));

                if (team == HomeOpponent.HOME.getId()) {
                    homeSuccessful.put(success, "S-" + setNum + " R-" + i);
                } else {
                    oppSuccessful.put(success, "S-" + setNum + " R-" + i);
                }

                if (team == HomeOpponent.HOME.getId()) {
                    homeWeakest.put(loss, "S-" + setNum + " R-" + i);
                } else {
                    oppWeakest.put(loss, "S-" + setNum + " R-" + i);
                }
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
        panSet1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblHome1 = new javax.swing.JLabel();
        panHome1 = new javax.swing.JPanel();
        lblOpp1 = new javax.swing.JLabel();
        panOpp1 = new javax.swing.JPanel();
        panSet2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblHome2 = new javax.swing.JLabel();
        panHome2 = new javax.swing.JPanel();
        lblOpp2 = new javax.swing.JLabel();
        panOpp2 = new javax.swing.JPanel();
        panSet3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblHome3 = new javax.swing.JLabel();
        panHome3 = new javax.swing.JPanel();
        lblOpp3 = new javax.swing.JLabel();
        panOpp3 = new javax.swing.JPanel();
        panSet4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblHome4 = new javax.swing.JLabel();
        panHome4 = new javax.swing.JPanel();
        lblOpp4 = new javax.swing.JLabel();
        panOpp4 = new javax.swing.JPanel();
        panSet5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblHome5 = new javax.swing.JLabel();
        panHome5 = new javax.swing.JPanel();
        lblOpp5 = new javax.swing.JLabel();
        panOpp5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOverall = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        panRotatonRows.setBackground(new java.awt.Color(0, 0, 0));

        panSet1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("SET 1");

        lblHome1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHome1.setText("HOME");

        javax.swing.GroupLayout panHome1Layout = new javax.swing.GroupLayout(panHome1);
        panHome1.setLayout(panHome1Layout);
        panHome1Layout.setHorizontalGroup(
            panHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panHome1Layout.setVerticalGroup(
            panHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        lblOpp1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOpp1.setText("HOME");

        javax.swing.GroupLayout panOpp1Layout = new javax.swing.GroupLayout(panOpp1);
        panOpp1.setLayout(panOpp1Layout);
        panOpp1Layout.setHorizontalGroup(
            panOpp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panOpp1Layout.setVerticalGroup(
            panOpp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panSet1Layout = new javax.swing.GroupLayout(panSet1);
        panSet1.setLayout(panSet1Layout);
        panSet1Layout.setHorizontalGroup(
            panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOpp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSet1Layout.createSequentialGroup()
                .addGroup(panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblHome1)
                    .addComponent(lblOpp1))
                .addGap(0, 959, Short.MAX_VALUE))
        );
        panSet1Layout.setVerticalGroup(
            panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(lblHome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblOpp1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panOpp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panSet2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("SET 2");

        lblHome2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHome2.setText("HOME");

        panHome2.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panHome2Layout = new javax.swing.GroupLayout(panHome2);
        panHome2.setLayout(panHome2Layout);
        panHome2Layout.setHorizontalGroup(
            panHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panHome2Layout.setVerticalGroup(
            panHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        lblOpp2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOpp2.setText("HOME");

        javax.swing.GroupLayout panOpp2Layout = new javax.swing.GroupLayout(panOpp2);
        panOpp2.setLayout(panOpp2Layout);
        panOpp2Layout.setHorizontalGroup(
            panOpp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panOpp2Layout.setVerticalGroup(
            panOpp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panSet2Layout = new javax.swing.GroupLayout(panSet2);
        panSet2.setLayout(panSet2Layout);
        panSet2Layout.setHorizontalGroup(
            panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOpp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSet2Layout.createSequentialGroup()
                .addGroup(panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblHome2)
                    .addComponent(lblOpp2))
                .addGap(0, 0, 0))
        );
        panSet2Layout.setVerticalGroup(
            panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(lblHome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblOpp2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panOpp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panSet3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("SET 3");

        lblHome3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHome3.setText("HOME");

        javax.swing.GroupLayout panHome3Layout = new javax.swing.GroupLayout(panHome3);
        panHome3.setLayout(panHome3Layout);
        panHome3Layout.setHorizontalGroup(
            panHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panHome3Layout.setVerticalGroup(
            panHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        lblOpp3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOpp3.setText("HOME");

        javax.swing.GroupLayout panOpp3Layout = new javax.swing.GroupLayout(panOpp3);
        panOpp3.setLayout(panOpp3Layout);
        panOpp3Layout.setHorizontalGroup(
            panOpp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panOpp3Layout.setVerticalGroup(
            panOpp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panSet3Layout = new javax.swing.GroupLayout(panSet3);
        panSet3.setLayout(panSet3Layout);
        panSet3Layout.setHorizontalGroup(
            panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOpp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSet3Layout.createSequentialGroup()
                .addGroup(panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblHome3)
                    .addComponent(lblOpp3))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panSet3Layout.setVerticalGroup(
            panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(lblHome3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblOpp3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panOpp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panSet4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("SET 4");

        lblHome4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHome4.setText("HOME");

        javax.swing.GroupLayout panHome4Layout = new javax.swing.GroupLayout(panHome4);
        panHome4.setLayout(panHome4Layout);
        panHome4Layout.setHorizontalGroup(
            panHome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panHome4Layout.setVerticalGroup(
            panHome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        lblOpp4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOpp4.setText("HOME");

        javax.swing.GroupLayout panOpp4Layout = new javax.swing.GroupLayout(panOpp4);
        panOpp4.setLayout(panOpp4Layout);
        panOpp4Layout.setHorizontalGroup(
            panOpp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panOpp4Layout.setVerticalGroup(
            panOpp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panSet4Layout = new javax.swing.GroupLayout(panSet4);
        panSet4.setLayout(panSet4Layout);
        panSet4Layout.setHorizontalGroup(
            panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHome4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOpp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSet4Layout.createSequentialGroup()
                .addGroup(panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lblHome4)
                    .addComponent(lblOpp4))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panSet4Layout.setVerticalGroup(
            panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(lblHome4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblOpp4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panOpp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panSet5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("SET 5");

        lblHome5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHome5.setText("HOME");

        javax.swing.GroupLayout panHome5Layout = new javax.swing.GroupLayout(panHome5);
        panHome5.setLayout(panHome5Layout);
        panHome5Layout.setHorizontalGroup(
            panHome5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panHome5Layout.setVerticalGroup(
            panHome5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        lblOpp5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOpp5.setText("HOME");

        javax.swing.GroupLayout panOpp5Layout = new javax.swing.GroupLayout(panOpp5);
        panOpp5.setLayout(panOpp5Layout);
        panOpp5Layout.setHorizontalGroup(
            panOpp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panOpp5Layout.setVerticalGroup(
            panOpp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panSet5Layout = new javax.swing.GroupLayout(panSet5);
        panSet5.setLayout(panSet5Layout);
        panSet5Layout.setHorizontalGroup(
            panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panHome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOpp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSet5Layout.createSequentialGroup()
                .addGroup(panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblHome5)
                    .addComponent(lblOpp5))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panSet5Layout.setVerticalGroup(
            panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblHome5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHome5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblOpp5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panOpp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout panRotatonRowsLayout = new javax.swing.GroupLayout(panRotatonRows);
        panRotatonRows.setLayout(panRotatonRowsLayout);
        panRotatonRowsLayout.setHorizontalGroup(
            panRotatonRowsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panSet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panRotatonRowsLayout.setVerticalGroup(
            panRotatonRowsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRotatonRowsLayout.createSequentialGroup()
                .addComponent(panSet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panSet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panSet3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panSet4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panSet5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        tbOverall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TEAM", "STRONGEST", "WEAKEST", "FAVOURABLE", "NON FAVOURABLE"
            }
        ));
        tbOverall.setRowHeight(30);
        jScrollPane1.setViewportView(tbOverall);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("OVERALL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panRotatonRows, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panRotatonRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHome1;
    private javax.swing.JLabel lblHome2;
    private javax.swing.JLabel lblHome3;
    private javax.swing.JLabel lblHome4;
    private javax.swing.JLabel lblHome5;
    private javax.swing.JLabel lblOpp1;
    private javax.swing.JLabel lblOpp2;
    private javax.swing.JLabel lblOpp3;
    private javax.swing.JLabel lblOpp4;
    private javax.swing.JLabel lblOpp5;
    private javax.swing.JPanel panHome1;
    private javax.swing.JPanel panHome2;
    private javax.swing.JPanel panHome3;
    private javax.swing.JPanel panHome4;
    private javax.swing.JPanel panHome5;
    private javax.swing.JPanel panOpp1;
    private javax.swing.JPanel panOpp2;
    private javax.swing.JPanel panOpp3;
    private javax.swing.JPanel panOpp4;
    private javax.swing.JPanel panOpp5;
    private javax.swing.JPanel panRotatonRows;
    private javax.swing.JPanel panSet1;
    private javax.swing.JPanel panSet2;
    private javax.swing.JPanel panSet3;
    private javax.swing.JPanel panSet4;
    private javax.swing.JPanel panSet5;
    private javax.swing.JTable tbOverall;
    // End of variables declaration//GEN-END:variables
}

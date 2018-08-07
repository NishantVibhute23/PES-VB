/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.Team;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogAllScoreChart;
import com.vollyball.dialog.DialogMatchDetails;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanTeamDetail extends javax.swing.JPanel {

    JTable tbReport, tbMatch, tbOverall;
    DefaultTableModel dm, dmMatch, dmOverall;
    ReportDao reportDao = new ReportDao();
    List<Player> playerList;
    List<Integer> evaluationteamId;
    TeamDao td = new TeamDao();
    Team t;

    int cb;
    Map<String, PlayerScores> teamNameMap = new HashMap<String, PlayerScores>();
    Map<String, Player> playerNameMap = new HashMap<String, Player>();
    Map<String, PlayerScores> matchNameMap = new HashMap<String, PlayerScores>();

    /**
     * Creates new form PanTeamDetail
     */
    public PanTeamDetail(final int cb, int teamId) {
        initComponents();
        this.cb = cb;

        t = td.getteamDetail(teamId);
        lblTEamName.setText(t.getName());
        lblSC.setText(t.getShortCode());
        lblCoachName.setText(t.getCoach());
        lblAssCN.setText(t.getAsstCoach());
        lblAN.setText(t.getAnalyzer());
        lblTN.setText(t.getTrainer());
        lblMON.setText(t.getMedicalOffice());

        playerList = td.getTeamPlayers(teamId);
        evaluationteamId = reportDao.getTeamEvaluationId(teamId);
        createTable();
        createMatchTable();
        createOverallTable();

        setRow();
        setMatchRow();
        setTeamRow(t);

        for (Player p : playerList) {
            playerNameMap.put(p.getName(), p);
        }

        tbOverall.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = null, teamName = "";
                    int matchesPlayed = 0;
                    int selectedRow = tbOverall.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedName = (String) tbOverall.getValueAt(selectedRow, 1);

                    }
                    if (selectedName != null) {
                        tbOverall.clearSelection();
                        DialogAllScoreChart createDialogPanMatchWiseReport = new DialogAllScoreChart();
                        createDialogPanMatchWiseReport.init(cb, 0, selectedName, matchesPlayed, teamName, 0, teamNameMap.get(selectedName).getId());
                        createDialogPanMatchWiseReport.show();
                    }
                }
            }
        });

        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = null, teamName = "";
                    int matchesPlayed = 0;
                    int selectedRow = tbReport.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedName = (String) tbReport.getValueAt(selectedRow, 0);
                        teamName = t.getName();
                        matchesPlayed = (int) tbReport.getValueAt(selectedRow, 1);
                    }
                    if (selectedName != null) {
                        tbReport.clearSelection();
                        DialogAllScoreChart createDialogPanMatchWiseReport = new DialogAllScoreChart();
                        createDialogPanMatchWiseReport.init(cb, playerNameMap.get(selectedName).getId(), selectedName, matchesPlayed, teamName, 0, 0);
                        createDialogPanMatchWiseReport.show();
                    }
                }
            }
        });

        tbMatch.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = null, phase = "";
                    int matchesPlayed = 0;
                    int selectedRow = tbMatch.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedName = (String) tbMatch.getValueAt(selectedRow, 0);
                        phase = (String) tbMatch.getValueAt(selectedRow, 1);
                    }
                    if (selectedName != null) {
                        tbMatch.clearSelection();
                        int id = matchNameMap.get(selectedName + "-" + phase).getId();
                        DialogMatchDetails createDialogPanMatchWiseReport = new DialogMatchDetails();
                        createDialogPanMatchWiseReport.init(cb, id);
                        createDialogPanMatchWiseReport.show();
                    }
                }
            }
        });
    }

    public void setTeamRow(Team Team) {
        List<PlayerScores> playerScoresList = new ArrayList<>();

        for (int i = dmOverall.getRowCount() - 1; i >= 0; i--) {
            dmOverall.removeRow(i);
        }

        PlayerScores playerScore = reportDao.getTeamScores(Team);
        playerScoresList.add(playerScore);

        int i = 0;
        for (PlayerScores p : playerScoresList) {
            teamNameMap.put(p.getPlayerName(), p);
            Object[] row = {i + 1, p.getPlayerName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc()};
            dmOverall.addRow(row);
            i++;
        }

    }

    public void setRow() {
        List<PlayerScores> playerScoresList = new ArrayList<>();
        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        for (Player p : playerList) {
            PlayerScores playerScore = reportDao.getPlayerScores(cb, p, 0);
            playerScoresList.add(playerScore);
        }
        Collections.sort(playerScoresList, new Comparator<PlayerScores>() {
            @Override
            public int compare(PlayerScores c1, PlayerScores c2) {
                return Double.compare(c2.getAttemptRate(), c1.getAttemptRate());
            }
        });
        int i = 0;
        for (PlayerScores p : playerScoresList) {
            Object[] row = {p.getPlayerName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc()};
            dm.addRow(row);
            i++;
        }
    }

    public void setMatchRow() {

        List<PlayerScores> playerScoresList = new ArrayList<>();
        for (int i = dmMatch.getRowCount() - 1; i >= 0; i--) {
            dmMatch.removeRow(i);
        }
        for (int p : evaluationteamId) {
            PlayerScores playerScore = reportDao.getTeamMatchScores(p);
            playerScoresList.add(playerScore);
        }
        Collections.sort(playerScoresList, new Comparator<PlayerScores>() {
            @Override
            public int compare(PlayerScores c1, PlayerScores c2) {
                return Double.compare(c2.getAttemptRate(), c1.getAttemptRate());
            }
        });

        int i = 0;
        for (PlayerScores p : playerScoresList) {
            matchNameMap.put(p.getPlayerName() + "-" + p.getMatchPhase(), p);
            Object[] row = {p.getPlayerName(), p.getMatchPhase(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc()};
            dmMatch.addRow(row);
            i++;
        }
    }

    public void createTable() {
        dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        dm.setDataVector(new Object[][]{},
                new Object[]{"Player Name", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 12));
        TableColumnModel cm = tbReport.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(2));
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));

        GroupableTableHeader header = (GroupableTableHeader) tbReport.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbReport);

        Color heading = new Color(204, 204, 204);
        dm = (DefaultTableModel) tbReport.getModel();

        JTableHeader tbheader = tbReport.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 45));
        tbheader.setDefaultRenderer(new TableHeaderRenderer(tbReport));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbReport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(25);
        resizeColumns();
        panPlayerReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {35.0f, 8.0f, 7.0f, 7.0f, 7.0f, 7.0f, 10.0f, 7.0f, 7.0f};

    private void resizeColumns() {
        int tW = tbReport.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbReport.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void createMatchTable() {
        dmMatch = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        dmMatch.setDataVector(new Object[][]{},
                new Object[]{"Match", "Phase", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        tbMatch = new JTable(dmMatch) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbMatch.setFont(new java.awt.Font("Times New Roman", 0, 12));
        TableColumnModel cm = tbMatch.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(2));
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));

        GroupableTableHeader header = (GroupableTableHeader) tbMatch.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbMatch);

        Color heading = new Color(204, 204, 204);
        dmMatch = (DefaultTableModel) tbMatch.getModel();

        JTableHeader tbheader = tbMatch.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 45));
        tbheader.setDefaultRenderer(new TableHeaderRenderer(tbMatch));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbMatch.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbMatch.setOpaque(true);
        tbMatch.setFillsViewportHeight(true);
        tbMatch.setBackground(ivory);

        tbMatch.setRowHeight(25);
        resizeMatchColumns();
        PanMatchReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthMatchPercentage = {28.0f, 15.0f, 7.0f, 7.0f, 7.0f, 7.0f, 10.0f, 7.0f, 7.0f};

    private void resizeMatchColumns() {
        int tW = tbMatch.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbMatch.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthMatchPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void createOverallTable() {
        dmOverall = new DefaultTableModel();

        dmOverall.setDataVector(new Object[][]{},
                new Object[]{"SNo.", "Team Name", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total"});

        tbOverall = new JTable(dmOverall) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbOverall.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbOverall.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));

        GroupableTableHeader header = (GroupableTableHeader) tbOverall.getTableHeader();
        header.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbOverall);

        Color heading = new Color(204, 204, 204);
        dmOverall = (DefaultTableModel) tbOverall.getModel();
        JTableHeader tbheader = tbOverall.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 45));
        tbheader.setDefaultRenderer(new TableHeaderRenderer(tbOverall));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbOverall.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(0).setWidth(10);

        tbOverall.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbOverall.setOpaque(true);
        tbOverall.setFillsViewportHeight(true);
        tbOverall.setBackground(ivory);

        tbOverall.setRowHeight(30);
        resizeOverallColumns();
        panTeamDetail.add(scroll, BorderLayout.CENTER);
    }
    float[] columntbOverallWidthPercentage = {5.0f, 23.0f, 9.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeOverallColumns() {
        int tW = tbOverall.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbOverall.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columntbOverallWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
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
        jLabel1 = new javax.swing.JLabel();
        panPlayerReport = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanMatchReport = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblTEamName = new javax.swing.JLabel();
        panPrint = new javax.swing.JPanel();
        lblPrint = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblSC = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTN = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblAN = new javax.swing.JLabel();
        lblCoachName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAssCN = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMON = new javax.swing.JLabel();
        panTeamDetail = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLAYERS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        panPlayerReport.setBackground(new java.awt.Color(255, 255, 255));
        panPlayerReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panPlayerReport.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MATCHES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        PanMatchReport.setBackground(new java.awt.Color(255, 255, 255));
        PanMatchReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanMatchReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panPlayerReport, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(PanMatchReport, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanMatchReport, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addComponent(panPlayerReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(57, 74, 108));

        jLabel4.setBackground(new java.awt.Color(251, 205, 1));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TEAM : ");

        lblTEamName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTEamName.setForeground(new java.awt.Color(255, 255, 255));

        panPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        lblPrint.setToolTipText("Print This Page");
        lblPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrintLayout = new javax.swing.GroupLayout(panPrint);
        panPrint.setLayout(panPrintLayout);
        panPrintLayout.setHorizontalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPrintLayout.createSequentialGroup()
                .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );
        panPrintLayout.setVerticalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPrintLayout.createSequentialGroup()
                .addComponent(lblPrint)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTEamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(678, 678, 678)
                .addComponent(panPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTEamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Short Code : ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Trainer Name : ");

        lblTN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("Analyzer Name : ");

        lblAN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        lblCoachName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Coach Name : ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Asst. Coach Name : ");

        lblAssCN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Medical Officer Name : ");

        lblMON.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblSC, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCoachName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAN, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAssCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSC)
                    .addComponent(jLabel6)
                    .addComponent(lblCoachName)
                    .addComponent(jLabel8)
                    .addComponent(lblAssCN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTN)
                    .addComponent(jLabel11)
                    .addComponent(lblAN)
                    .addComponent(jLabel13)
                    .addComponent(lblMON))
                .addGap(11, 11, 11))
        );

        panTeamDetail.setBackground(new java.awt.Color(255, 255, 255));
        panTeamDetail.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(57, 74, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("OVERALL");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panTeamDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panTeamDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseClicked
        // TODO add your handling code here:
        panPrint.setVisible(false);

        printComponenet(this);
        panPrint.setVisible(true);
    }//GEN-LAST:event_lblPrintMouseClicked

    public void printComponenet(final Component comp) {

        PageFormat documentPageFormat = new PageFormat();
        documentPageFormat.setOrientation(PageFormat.LANDSCAPE);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Score Report_-_" + t.getName());

        pj.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat format, int page_index)
                    throws PrinterException {
                if (page_index > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                format.setOrientation(PageFormat.LANDSCAPE);
                // get the bounds of the component
                Dimension dim = comp.getSize();
                double cHeight = dim.getHeight();
                double cWidth = dim.getWidth();

                // get the bounds of the printable area
                double pHeight = format.getImageableHeight();
                double pWidth = format.getImageableWidth();

                double pXStart = format.getImageableX();
                double pYStart = format.getImageableY();

                double xRatio = pWidth / cWidth;
                double yRatio = pHeight / cHeight;

                Graphics2D g2 = (Graphics2D) g;
                g2.translate(pXStart, pYStart);
                g2.scale(xRatio, yRatio);
                comp.printAll(g2);

                return Printable.PAGE_EXISTS;
            }
        }, documentPageFormat);
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanMatchReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAN;
    private javax.swing.JLabel lblAssCN;
    private javax.swing.JLabel lblCoachName;
    private javax.swing.JLabel lblMON;
    private javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblSC;
    private javax.swing.JLabel lblTEamName;
    private javax.swing.JLabel lblTN;
    private javax.swing.JPanel panPlayerReport;
    private javax.swing.JPanel panPrint;
    private javax.swing.JPanel panTeamDetail;
    // End of variables declaration//GEN-END:variables
}

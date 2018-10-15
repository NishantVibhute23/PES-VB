/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels.report;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.SuccessFailure;
import com.vollyball.bean.ZoneHitCount;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.enums.Rating;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDescCriteriaPoint;
import com.vollyball.enums.SkillZoneWiseReport;
import com.vollyball.enums.SkillsDescCriteria;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRendererForReport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanZoneSkillwiseMain extends javax.swing.JPanel {

//    List<JPanel> rotationPanels = new ArrayList<>();
//    List<JPanel> skillPanels = new ArrayList<>();
    List<SkillZoneWiseReport> listZoneDetails = new ArrayList<SkillZoneWiseReport>();
    ReportDao reportDao = new ReportDao();
    MatchDao matchDao = new MatchDao();
    int team1id, team2id;
    int evaluationteamIdHome, evaluationteamIdOpp;
    LinkedHashMap<Integer, ZoneHitCount> zoneCountMap = new LinkedHashMap<>();

    int maxSuccPerc = 0;
    int maxFailPerc = 0;
    int successZone = 0;
    int failedZone = 0;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    DefaultTableModel defaultHomeServiceModel;
    DefaultTableModel defaultHomeAttackModel;
    DefaultTableModel defaultHomeBlockModel;
    DefaultTableModel defaultHomeSetModel;
    DefaultTableModel defaultHomeReceptionModel;
    DefaultTableModel defaultHomeDefenceModel;
    DefaultTableModel defaultOppServiceModel;
    DefaultTableModel defaultOppAttackModel;
    DefaultTableModel defaultOppBlockModel;
    DefaultTableModel defaultOppSetModel;
    DefaultTableModel defaultOppReceptionModel;
    DefaultTableModel defaultOppDefenceModel;
    JTable tbHomeServiceTable;
    JTable tbHomeAttackTable;
    JTable tbHomeBlockTable;
    JTable tbHomeSetTable;
    JTable tbHomeReceptionTable;
    JTable tbHomeDefenceTable;
    JTable tbOppServiceTable;
    JTable tbOppAttackTable;
    JTable tbOppBlockTable;
    JTable tbOppSetTable;
    JTable tbOppReceptionTable;
    JTable tbOppDefenceTable;

    /**
     * Creates new form PanZoneReport
     */
    public PanZoneSkillwiseMain(int cb, int matchId) {
        initComponents();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();

        lblHomeTeamName.setText(team.getTeam1name());
        lblOppTeamName.setText(team.getTeam2name());

        evaluationteamIdHome = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamIdOpp = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        listZoneDetails = reportDao.getZoneDetails(Skill.Service.getId(), SkillsDescCriteria.ServiceDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeService, tblHomeService);

        listZoneDetails = reportDao.getZoneDetails(Skill.Attack.getId(), SkillsDescCriteria.AttackDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeAttack, tblHomeAttack);

        listZoneDetails = reportDao.getZoneDetails(Skill.Block.getId(), SkillsDescCriteria.BlockDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeBlock, tblHomeBlock);

        listZoneDetails = reportDao.getZoneDetails(Skill.Reception.getId(), SkillsDescCriteria.ReceptionDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeReception, tblHomeReception);

        listZoneDetails = reportDao.getZoneDetails(Skill.Set.getId(), SkillsDescCriteria.SetDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeSet, tblHomeSet);

        listZoneDetails = reportDao.getZoneDetails(Skill.Defence.getId(), SkillsDescCriteria.DefenceDig.getId(), evaluationteamIdHome);
        setData(listZoneDetails, digHomeDefence, tblHomeDefence);

        //  opponent
        listZoneDetails = reportDao.getZoneDetails(Skill.Service.getId(), SkillsDescCriteria.ServiceDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppService, tblOppService);

        listZoneDetails = reportDao.getZoneDetails(Skill.Attack.getId(), SkillsDescCriteria.AttackDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppAttack, tblOppAttack);

        listZoneDetails = reportDao.getZoneDetails(Skill.Block.getId(), SkillsDescCriteria.BlockDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppBlock, tblOppBlock);

        listZoneDetails = reportDao.getZoneDetails(Skill.Reception.getId(), SkillsDescCriteria.ReceptionDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppReception, tblOppReception);

        listZoneDetails = reportDao.getZoneDetails(Skill.Set.getId(), SkillsDescCriteria.SetDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppSet, tblOppSet);

        listZoneDetails = reportDao.getZoneDetails(Skill.Defence.getId(), SkillsDescCriteria.DefenceDig.getId(), evaluationteamIdOpp);
        setData(listZoneDetails, digOppDefence, tblOppDefence);

        createBlockTable(tbHomeBlockTable, panHomeBlockTable, defaultHomeBlockModel = new DefaultTableModel());
        createServiceTable(tbHomeServiceTable, panHomeServiceTable, defaultHomeServiceModel = new DefaultTableModel());
        createAttackTable(tbHomeAttackTable, panHomeAttackTable, defaultHomeAttackModel = new DefaultTableModel());
        createServiceTable(tbOppServiceTable, panOppServiceTable, defaultOppServiceModel = new DefaultTableModel());
        createAttackTable(tbOppAttackTable, panOppAttackTable, defaultOppAttackModel = new DefaultTableModel());
        createBlockTable(tbOppBlockTable, panOppBlockTable, defaultOppBlockModel = new DefaultTableModel());
        createSetTable(tbHomeSetTable, panHomeSetTable, defaultHomeSetModel = new DefaultTableModel());
        createSetTable(tbOppSetTable, panOppSetTable, defaultOppSetModel = new DefaultTableModel());
        createReceptionTable(tbHomeReceptionTable, panHomeReceptionTable, defaultHomeReceptionModel = new DefaultTableModel());
        createReceptionTable(tbOppReceptionTable, panOppReceptionTable, defaultOppReceptionModel = new DefaultTableModel());
        createDefenceTable(tbHomeDefenceTable, panHomeDefenceTable, defaultHomeDefenceModel = new DefaultTableModel());
        createDefenceTable(tbOppDefenceTable, panOppDefenceTable, defaultOppDefenceModel = new DefaultTableModel());
        setAttackRow(defaultHomeAttackModel, evaluationteamIdHome, 11, 2);
        setAttackRow(defaultOppAttackModel, evaluationteamIdOpp, 11, 2);
        setBlockRow(defaultHomeBlockModel, evaluationteamIdHome, 37, 3);
        setBlockRow(defaultOppBlockModel, evaluationteamIdOpp, 37, 3);
        setServiceRow(defaultHomeServiceModel, evaluationteamIdHome, 1, 1);
        setServiceRow(defaultOppServiceModel, evaluationteamIdOpp, 1, 1);
        setSetRow(defaultHomeSetModel, evaluationteamIdHome, 43, 4);
        setSetRow(defaultOppSetModel, evaluationteamIdOpp, 43, 4);
        setReceptionRow(defaultHomeReceptionModel, evaluationteamIdHome, 60, 5);
        setReceptionRow(defaultOppReceptionModel, evaluationteamIdOpp, 60, 5);
        setDefenceRow(defaultHomeDefenceModel, evaluationteamIdHome, 75, 6);
        setDefenceRow(defaultOppDefenceModel, evaluationteamIdOpp, 75, 6);

    }

    public void createServiceTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameJf = new ColumnGroup("JF");
        g_nameJf.add(cm.getColumn(0));
        g_nameJf.add(cm.getColumn(1));
        ColumnGroup g_nameJp = new ColumnGroup("JP");
        g_nameJp.add(cm.getColumn(2));
        g_nameJp.add(cm.getColumn(3));
        ColumnGroup g_nameSf = new ColumnGroup("SF");
        g_nameSf.add(cm.getColumn(4));
        g_nameSf.add(cm.getColumn(5));
        ColumnGroup g_nameSs = new ColumnGroup("SS");
        g_nameSs.add(cm.getColumn(6));
        g_nameSs.add(cm.getColumn(7));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameJf);
        header.addColumnGroup(g_nameJp);
        header.addColumnGroup(g_nameSf);
        header.addColumnGroup(g_nameSs);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns1(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }

    public void createAttackTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameIn = new ColumnGroup("IN");
        g_nameIn.add(cm.getColumn(0));
        g_nameIn.add(cm.getColumn(1));
        ColumnGroup g_nameOt = new ColumnGroup("OT");
        g_nameOt.add(cm.getColumn(2));
        g_nameOt.add(cm.getColumn(3));
        ColumnGroup g_nameBt = new ColumnGroup("BT");
        g_nameBt.add(cm.getColumn(4));
        g_nameBt.add(cm.getColumn(5));
        ColumnGroup g_nameOl = new ColumnGroup("OL");
        g_nameOl.add(cm.getColumn(6));
        g_nameOl.add(cm.getColumn(7));
        ColumnGroup g_nameD = new ColumnGroup("D");
        g_nameD.add(cm.getColumn(8));
        g_nameD.add(cm.getColumn(9));
        ColumnGroup g_nameBc = new ColumnGroup("BC");
        g_nameBc.add(cm.getColumn(10));
        g_nameBc.add(cm.getColumn(11));
        ColumnGroup g_nameR = new ColumnGroup("R");
        g_nameR.add(cm.getColumn(12));
        g_nameR.add(cm.getColumn(13));
        ColumnGroup g_nameBtl = new ColumnGroup("BTL");
        g_nameBtl.add(cm.getColumn(14));
        g_nameBtl.add(cm.getColumn(15));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameIn);
        header.addColumnGroup(g_nameOt);
        header.addColumnGroup(g_nameBt);
        header.addColumnGroup(g_nameOl);
        header.addColumnGroup(g_nameD);
        header.addColumnGroup(g_nameBc);
        header.addColumnGroup(g_nameR);
        header.addColumnGroup(g_nameBtl);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }
    float[] columnWidthPercentage = {8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumns(JTable tbTable) {
        int tW = tbTable.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTable.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void createBlockTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameNb = new ColumnGroup("NB");
        g_nameNb.add(cm.getColumn(0));
        g_nameNb.add(cm.getColumn(1));
        ColumnGroup g_namesbl = new ColumnGroup("SGL");
        g_namesbl.add(cm.getColumn(2));
        g_namesbl.add(cm.getColumn(3));
        ColumnGroup g_namedbl = new ColumnGroup("DBL");
        g_namedbl.add(cm.getColumn(4));
        g_namedbl.add(cm.getColumn(5));
        ColumnGroup g_nametpl = new ColumnGroup("TPL");
        g_nametpl.add(cm.getColumn(6));
        g_nametpl.add(cm.getColumn(7));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameNb);
        header.addColumnGroup(g_namesbl);
        header.addColumnGroup(g_namedbl);
        header.addColumnGroup(g_nametpl);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns1(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }

    public void createSetTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameHigh = new ColumnGroup("HIGH");
        g_nameHigh.add(cm.getColumn(0));
        g_nameHigh.add(cm.getColumn(1));
        ColumnGroup g_nameMedium = new ColumnGroup("MEDIUM");
        g_nameMedium.add(cm.getColumn(2));
        g_nameMedium.add(cm.getColumn(3));
        ColumnGroup g_nameLow = new ColumnGroup("LOW");
        g_nameLow.add(cm.getColumn(4));
        g_nameLow.add(cm.getColumn(5));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameHigh);
        header.addColumnGroup(g_nameMedium);
        header.addColumnGroup(g_nameLow);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns2(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }

    float[] columnWidthPercentage2 = {8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumns2(JTable tbTable) {
        int tW = tbTable.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTable.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage2[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void createReceptionTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameOn = new ColumnGroup("ON");
        g_nameOn.add(cm.getColumn(0));
        g_nameOn.add(cm.getColumn(1));
        ColumnGroup g_nameCn = new ColumnGroup("CN");
        g_nameCn.add(cm.getColumn(2));
        g_nameCn.add(cm.getColumn(3));
        ColumnGroup g_nameAn = new ColumnGroup("AN");
        g_nameAn.add(cm.getColumn(4));
        g_nameAn.add(cm.getColumn(5));
        ColumnGroup g_nameLt = new ColumnGroup("LT");
        g_nameLt.add(cm.getColumn(6));
        g_nameLt.add(cm.getColumn(7));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameOn);
        header.addColumnGroup(g_nameCn);
        header.addColumnGroup(g_nameAn);
        header.addColumnGroup(g_nameLt);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns1(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }

    public void createDefenceTable(JTable tbTable, JPanel panTable, DefaultTableModel defaultModel) {
//        defaultModel = new DefaultTableModel();

        defaultModel.setDataVector(new Object[][]{},
                new Object[]{"<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>", "<html>+</html>", "<html>-</html>"});

        tbTable = new JTable(defaultModel) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbTable.setFont(new java.awt.Font("Times New Roman", 0, 8));
        TableColumnModel cm = tbTable.getColumnModel();

        ColumnGroup g_nameOn = new ColumnGroup("ON");
        g_nameOn.add(cm.getColumn(0));
        g_nameOn.add(cm.getColumn(1));
        ColumnGroup g_nameCn = new ColumnGroup("CN");
        g_nameCn.add(cm.getColumn(2));
        g_nameCn.add(cm.getColumn(3));
        ColumnGroup g_nameAn = new ColumnGroup("AN");
        g_nameAn.add(cm.getColumn(4));
        g_nameAn.add(cm.getColumn(5));
        ColumnGroup g_nameLt = new ColumnGroup("LT");
        g_nameLt.add(cm.getColumn(6));
        g_nameLt.add(cm.getColumn(7));

        GroupableTableHeader header = (GroupableTableHeader) tbTable.getTableHeader();
        header.addColumnGroup(g_nameOn);
        header.addColumnGroup(g_nameCn);
        header.addColumnGroup(g_nameAn);
        header.addColumnGroup(g_nameLt);
        JScrollPane scroll = new JScrollPane(tbTable);

        Color heading = new Color(204, 204, 204);
        defaultModel = (DefaultTableModel) tbTable.getModel();
        JTableHeader tbheader = tbTable.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 55));
        tbheader.setDefaultRenderer(new TableHeaderRendererForReport(tbTable));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(0).setWidth(5);

        tbTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        tbTable.setOpaque(true);
        tbTable.setFillsViewportHeight(true);
        tbTable.setBackground(ivory);

        tbTable.setRowHeight(25);
        resizeColumns1(tbTable);
        panTable.add(scroll, BorderLayout.CENTER);

    }

    float[] columnWidthPercentage1 = {8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumns1(JTable tbTable) {
        int tW = tbTable.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTable.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void setAttackRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(4).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(4).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(5).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(5).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(6).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(6).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(7).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(7).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setBlockRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setServiceRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setSetRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setReceptionRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setDefenceRow(DefaultTableModel defaultModel, int evlId, int skilDescId, int skillId) {
        for (int i = defaultModel.getRowCount() - 1; i >= 0; i--) {
            defaultModel.removeRow(i);
        }
        List<SkillDescCriteriaPoint> sdcPoint = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skilDescId);
        Map<String, SuccessFailure> sfMap = reportDao.getReportZoneSkillWise(evlId, skilDescId, skillId);
        Object[] row = {sfMap.get(sdcPoint.get(0).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(0).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(1).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(2).getAbbreviation()).getFailure(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getSuccess(), sfMap.get(sdcPoint.get(3).getAbbreviation()).getFailure()};
        defaultModel.addRow(row);
    }

    public void setData(List<SkillZoneWiseReport> listZoneDetails, JPanel panel, JTable table) {
        zoneCountMap.put(1, new ZoneHitCount());
        zoneCountMap.put(2, new ZoneHitCount());
        zoneCountMap.put(3, new ZoneHitCount());
        zoneCountMap.put(4, new ZoneHitCount());
        zoneCountMap.put(5, new ZoneHitCount());
        zoneCountMap.put(6, new ZoneHitCount());

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRendererForReport(table));
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
        for (int i = tablemodel.getRowCount() - 1; i >= 0; i--) {
            tablemodel.removeRow(i);
        }

        for (SkillZoneWiseReport skillZoneWiseReport : listZoneDetails) {
            String[] pan = skillZoneWiseReport.getValue().split("-");
            int index = 0;
            for (int i = 0; i < pan.length; i++) {
                if (pan[i].startsWith("H")) {
                    index = i;
                    break;
                }
            }

            if (pan[index].startsWith("H1")) {
                countHit(skillZoneWiseReport, 1);
            } else if (pan[index].startsWith("H2")) {
                countHit(skillZoneWiseReport, 2);
            } else if (pan[index].startsWith("H3")) {
                countHit(skillZoneWiseReport, 3);
            } else if (pan[index].startsWith("H4")) {
                countHit(skillZoneWiseReport, 4);
            } else if (pan[index].startsWith("H5")) {
                countHit(skillZoneWiseReport, 5);
            } else if (pan[index].startsWith("H6")) {
                countHit(skillZoneWiseReport, 6);
            }
        }

        panel.setLayout(new GridLayout(2, 3));

        int[] positions = {4, 3, 2, 5, 6, 1};

        for (int pos : positions) {
            ZoneHitCount zhc = zoneCountMap.get(pos);
            if (pos == successZone) {
                zhc.setIsMaxSuccess(true);
            }

            if (pos == failedZone) {
                zhc.setIsMaxFail(true);
            }
            JPanel pan = new JPanel(new BorderLayout());
            pan.setBorder(new LineBorder(new Color(153, 153, 153), 1));
            PanZoneSkillwisePanel p = new PanZoneSkillwisePanel();
            p.setValues(zhc);
            pan.add(p, BorderLayout.CENTER);
            panel.add(pan);

        }
        maxSuccPerc = 0;
        maxFailPerc = 0;
        successZone = 0;
        failedZone = 0;

        for (int i = 1; i <= 6; i++) {
            ZoneHitCount zhc = zoneCountMap.get(i);
            Object[] row = {i, zhc.getTotal(), zhc.getSuccess(), zhc.getFailure()
            };
            tablemodel.addRow(row);
        }

    }

    public void countHit(SkillZoneWiseReport skillZoneWiseReport, int pos) {
        ZoneHitCount z = zoneCountMap.get(pos);
        z.setTotal(z.getTotal() + 1);
        if (skillZoneWiseReport.getRating() == Rating.Excellent.getId()) {
            z.setSuccess(z.getSuccess() + 1);
        }
        if (skillZoneWiseReport.getRating() == Rating.Poor.getId()) {
            z.setFailure(z.getFailure() + 1);
        }
        z.setSuccessPerc((int) (Math.round((double) z.getSuccess() / z.getTotal() * 100)));
        z.setFailPerc((int) (Math.round((double) z.getFailure() / z.getTotal() * 100)));

        if (maxSuccPerc < z.getSuccessPerc()) {
            maxSuccPerc = z.getSuccessPerc();
            successZone = pos;
        }

        if (maxFailPerc < z.getFailure()) {
            maxFailPerc = z.getFailure();
            failedZone = pos;
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
        jLabel9 = new javax.swing.JLabel();
        digHomeService = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHomeService = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        panHomeServiceTable = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        digHomeAttack = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHomeAttack = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        panHomeAttackTable = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        digHomeBlock = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHomeBlock = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        panHomeBlockTable = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        digHomeReception = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHomeReception = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        panHomeReceptionTable = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        digHomeSet = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHomeSet = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        panHomeSetTable = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        digHomeDefence = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHomeDefence = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        panHomeDefenceTable = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblHomeTeamName = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        digOppDefence = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblOppDefence = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        panOppDefenceTable = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        digOppSet = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblOppSet = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        panOppSetTable = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        digOppService = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblOppService = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        panOppServiceTable = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        digOppReception = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblOppReception = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        panOppReceptionTable = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        digOppAttack = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblOppAttack = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        panOppAttackTable = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        digOppBlock = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblOppBlock = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        panOppBlockTable = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblOppTeamName = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Service");

        digHomeService.setBackground(new java.awt.Color(255, 255, 255));
        digHomeService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeServiceLayout = new javax.swing.GroupLayout(digHomeService);
        digHomeService.setLayout(digHomeServiceLayout);
        digHomeServiceLayout.setHorizontalGroup(
            digHomeServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeServiceLayout.setVerticalGroup(
            digHomeServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeService.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHomeService.setRowSelectionAllowed(false);
        tblHomeService.setShowHorizontalLines(false);
        tblHomeService.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblHomeService);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeServiceTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeServiceTable.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Service Type");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(digHomeService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panHomeServiceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panHomeServiceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Attack");

        digHomeAttack.setBackground(new java.awt.Color(255, 255, 255));
        digHomeAttack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeAttackLayout = new javax.swing.GroupLayout(digHomeAttack);
        digHomeAttack.setLayout(digHomeAttackLayout);
        digHomeAttackLayout.setHorizontalGroup(
            digHomeAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeAttackLayout.setVerticalGroup(
            digHomeAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeAttack.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeAttack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblHomeAttack.setRowSelectionAllowed(false);
        tblHomeAttack.setShowHorizontalLines(false);
        tblHomeAttack.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblHomeAttack);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeAttackTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeAttackTable.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Techniques");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digHomeAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(panHomeAttackTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panHomeAttackTable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("Block");

        digHomeBlock.setBackground(new java.awt.Color(255, 255, 255));
        digHomeBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeBlockLayout = new javax.swing.GroupLayout(digHomeBlock);
        digHomeBlock.setLayout(digHomeBlockLayout);
        digHomeBlockLayout.setHorizontalGroup(
            digHomeBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeBlockLayout.setVerticalGroup(
            digHomeBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeBlock.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeBlock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblHomeBlock.setRowSelectionAllowed(false);
        tblHomeBlock.setShowHorizontalLines(false);
        tblHomeBlock.setShowVerticalLines(false);
        jScrollPane3.setViewportView(tblHomeBlock);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeBlockTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeBlockTable.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("No of Block");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digHomeBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(panHomeBlockTable, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panHomeBlockTable, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setText("Reception");

        digHomeReception.setBackground(new java.awt.Color(255, 255, 255));
        digHomeReception.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeReceptionLayout = new javax.swing.GroupLayout(digHomeReception);
        digHomeReception.setLayout(digHomeReceptionLayout);
        digHomeReceptionLayout.setHorizontalGroup(
            digHomeReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeReceptionLayout.setVerticalGroup(
            digHomeReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeReception.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeReception.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblHomeReception.setRowSelectionAllowed(false);
        tblHomeReception.setShowHorizontalLines(false);
        tblHomeReception.setShowVerticalLines(false);
        jScrollPane4.setViewportView(tblHomeReception);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeReceptionTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeReceptionTable.setLayout(new java.awt.BorderLayout());

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel24.setText("Reception At");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digHomeReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(panHomeReceptionTable, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(10, 10, 10)
                .addComponent(panHomeReceptionTable, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Set");

        digHomeSet.setBackground(new java.awt.Color(255, 255, 255));
        digHomeSet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeSetLayout = new javax.swing.GroupLayout(digHomeSet);
        digHomeSet.setLayout(digHomeSetLayout);
        digHomeSetLayout.setHorizontalGroup(
            digHomeSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeSetLayout.setVerticalGroup(
            digHomeSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeSet.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblHomeSet.setRowSelectionAllowed(false);
        tblHomeSet.setShowHorizontalLines(false);
        tblHomeSet.setShowVerticalLines(false);
        jScrollPane5.setViewportView(tblHomeSet);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeSetTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeSetTable.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Tempo");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digHomeSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(panHomeSetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panHomeSetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Defence");

        digHomeDefence.setBackground(new java.awt.Color(255, 255, 255));
        digHomeDefence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digHomeDefenceLayout = new javax.swing.GroupLayout(digHomeDefence);
        digHomeDefence.setLayout(digHomeDefenceLayout);
        digHomeDefenceLayout.setHorizontalGroup(
            digHomeDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digHomeDefenceLayout.setVerticalGroup(
            digHomeDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblHomeDefence.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblHomeDefence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblHomeDefence.setRowSelectionAllowed(false);
        tblHomeDefence.setShowHorizontalLines(false);
        tblHomeDefence.setShowVerticalLines(false);
        jScrollPane6.setViewportView(tblHomeDefence);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panHomeDefenceTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panHomeDefenceTable.setLayout(new java.awt.BorderLayout());

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel21.setText("Defence At");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digHomeDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(panHomeDefenceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(11, 11, 11)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digHomeDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panHomeDefenceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        lblHomeTeamName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblHomeTeamName.setText("MUMBAI");

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setText("Defence");

        digOppDefence.setBackground(new java.awt.Color(255, 255, 255));
        digOppDefence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppDefenceLayout = new javax.swing.GroupLayout(digOppDefence);
        digOppDefence.setLayout(digOppDefenceLayout);
        digOppDefenceLayout.setHorizontalGroup(
            digOppDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppDefenceLayout.setVerticalGroup(
            digOppDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppDefence.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppDefence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppDefence.setRowSelectionAllowed(false);
        tblOppDefence.setShowHorizontalLines(false);
        tblOppDefence.setShowVerticalLines(false);
        jScrollPane7.setViewportView(tblOppDefence);

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppDefenceTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppDefenceTable.setLayout(new java.awt.BorderLayout());

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel22.setText("Defence At");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digOppDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(panOppDefenceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(11, 11, 11)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addGap(14, 14, 14)
                .addComponent(panOppDefenceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setText("Set");

        digOppSet.setBackground(new java.awt.Color(255, 255, 255));
        digOppSet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppSetLayout = new javax.swing.GroupLayout(digOppSet);
        digOppSet.setLayout(digOppSetLayout);
        digOppSetLayout.setHorizontalGroup(
            digOppSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppSetLayout.setVerticalGroup(
            digOppSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppSet.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppSet.setRowSelectionAllowed(false);
        tblOppSet.setShowHorizontalLines(false);
        tblOppSet.setShowVerticalLines(false);
        jScrollPane8.setViewportView(tblOppSet);

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppSetTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppSetTable.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Tempo");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digOppSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(panOppSetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(15, 15, 15)
                .addComponent(panOppSetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setText("Service");

        digOppService.setBackground(new java.awt.Color(255, 255, 255));
        digOppService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppServiceLayout = new javax.swing.GroupLayout(digOppService);
        digOppService.setLayout(digOppServiceLayout);
        digOppServiceLayout.setHorizontalGroup(
            digOppServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppServiceLayout.setVerticalGroup(
            digOppServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppService.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppService.setRowSelectionAllowed(false);
        tblOppService.setShowHorizontalLines(false);
        tblOppService.setShowVerticalLines(false);
        jScrollPane9.setViewportView(tblOppService);

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppServiceTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppServiceTable.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Service Type");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(digOppService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(panOppServiceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(11, 11, 11)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panOppServiceTable, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel18.setText("Reception");

        digOppReception.setBackground(new java.awt.Color(255, 255, 255));
        digOppReception.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppReceptionLayout = new javax.swing.GroupLayout(digOppReception);
        digOppReception.setLayout(digOppReceptionLayout);
        digOppReceptionLayout.setHorizontalGroup(
            digOppReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppReceptionLayout.setVerticalGroup(
            digOppReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppReception.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppReception.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppReception.setRowSelectionAllowed(false);
        tblOppReception.setShowHorizontalLines(false);
        tblOppReception.setShowVerticalLines(false);
        jScrollPane10.setViewportView(tblOppReception);

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppReceptionTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppReceptionTable.setLayout(new java.awt.BorderLayout());

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setText("Reception At");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digOppReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(panOppReceptionTable, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(11, 11, 11)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(14, 14, 14)
                .addComponent(panOppReceptionTable, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel19.setText("Attack");

        digOppAttack.setBackground(new java.awt.Color(255, 255, 255));
        digOppAttack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppAttackLayout = new javax.swing.GroupLayout(digOppAttack);
        digOppAttack.setLayout(digOppAttackLayout);
        digOppAttackLayout.setHorizontalGroup(
            digOppAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppAttackLayout.setVerticalGroup(
            digOppAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppAttack.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppAttack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppAttack.setRowSelectionAllowed(false);
        tblOppAttack.setShowHorizontalLines(false);
        tblOppAttack.setShowVerticalLines(false);
        jScrollPane11.setViewportView(tblOppAttack);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppAttackTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppAttackTable.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Technique");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(digOppAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel19))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(panOppAttackTable, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(11, 11, 11)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panOppAttackTable, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel20.setText("Block");

        digOppBlock.setBackground(new java.awt.Color(255, 255, 255));
        digOppBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout digOppBlockLayout = new javax.swing.GroupLayout(digOppBlock);
        digOppBlock.setLayout(digOppBlockLayout);
        digOppBlockLayout.setHorizontalGroup(
            digOppBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        digOppBlockLayout.setVerticalGroup(
            digOppBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        tblOppBlock.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblOppBlock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null}
            },
            new String [] {
                "Zone", "Tot", "+", "-"
            }
        ));
        tblOppBlock.setRowSelectionAllowed(false);
        tblOppBlock.setShowHorizontalLines(false);
        tblOppBlock.setShowVerticalLines(false);
        jScrollPane12.setViewportView(tblOppBlock);

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panOppBlockTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panOppBlockTable.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("No of Block");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(digOppBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(panOppBlockTable, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(11, 11, 11)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(digOppBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panOppBlockTable, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        lblOppTeamName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOppTeamName.setText("DELHI");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Zonewise Skill Performance");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHomeTeamName)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblOppTeamName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHomeTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblOppTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel digHomeAttack;
    private javax.swing.JPanel digHomeBlock;
    private javax.swing.JPanel digHomeDefence;
    private javax.swing.JPanel digHomeReception;
    private javax.swing.JPanel digHomeService;
    private javax.swing.JPanel digHomeSet;
    private javax.swing.JPanel digOppAttack;
    private javax.swing.JPanel digOppBlock;
    private javax.swing.JPanel digOppDefence;
    private javax.swing.JPanel digOppReception;
    private javax.swing.JPanel digOppService;
    private javax.swing.JPanel digOppSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblHomeTeamName;
    private javax.swing.JLabel lblOppTeamName;
    private javax.swing.JPanel panHomeAttackTable;
    private javax.swing.JPanel panHomeBlockTable;
    private javax.swing.JPanel panHomeDefenceTable;
    private javax.swing.JPanel panHomeReceptionTable;
    private javax.swing.JPanel panHomeServiceTable;
    private javax.swing.JPanel panHomeSetTable;
    private javax.swing.JPanel panOppAttackTable;
    private javax.swing.JPanel panOppBlockTable;
    private javax.swing.JPanel panOppDefenceTable;
    private javax.swing.JPanel panOppReceptionTable;
    private javax.swing.JPanel panOppServiceTable;
    private javax.swing.JPanel panOppSetTable;
    private javax.swing.JTable tblHomeAttack;
    private javax.swing.JTable tblHomeBlock;
    private javax.swing.JTable tblHomeDefence;
    private javax.swing.JTable tblHomeReception;
    private javax.swing.JTable tblHomeService;
    private javax.swing.JTable tblHomeSet;
    private javax.swing.JTable tblOppAttack;
    private javax.swing.JTable tblOppBlock;
    private javax.swing.JTable tblOppDefence;
    private javax.swing.JTable tblOppReception;
    private javax.swing.JTable tblOppService;
    private javax.swing.JTable tblOppSet;
    // End of variables declaration//GEN-END:variables
}

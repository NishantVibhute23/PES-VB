package com.vollyball.panels;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogEvaluationSubstitute;
import com.vollyball.dialog.DialogEvaluationTimeout;
import com.vollyball.dialog.DialogPanEvaluationRotationOrder;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluation extends javax.swing.JPanel {

    PanEvaluationRally pw;

    public int currentRally = 0;
    int setNum;
    int matchId;
    public int teamEvaluateId;
    public int opponentId;
    public int matchEvaluationId = 0;
    public int rallyNumNext = 0, totalRallies = 0;
    public DialogPanEvaluationRotationOrder setRotationDialog;
    public DialogEvaluationSubstitute dialogEvaluationSubstitute;
    public DialogEvaluationTimeout dialogEvaluationTimeout;
    public LinkedHashMap<Integer, RallyEvaluation> rallyMap = new LinkedHashMap<Integer, RallyEvaluation>();
    public LinkedHashMap<Integer, PanRallyLiveEvaluation> panRallyMap = new LinkedHashMap<Integer, PanRallyLiveEvaluation>();
    public LinkedHashMap<Integer, Player> initialPositionMap;
    public LinkedHashMap<Integer, Player> initialPositionMapOpp;
    public LinkedHashMap<Integer, Player> rallyPositionMap;
    public LinkedHashMap<Integer, Player> rallyPositionMapOpp;
    public LinkedHashMap<Integer, Player> substituePositionMap;

    public LinkedHashMap<Integer, Player> playerMapOpp = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMapOpp = new LinkedHashMap<String, Player>();
    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<String, Player>();
    MatchDao matchDao = new MatchDao();
    TeamDao teamDao = new TeamDao();
    RallyDao rallyDao = new RallyDao();
    PanRallyLiveEvaluation panRallyCurrent;
    int totalRally = 0;

    List<Player> playerList, playerListOpp;
    public int homeScore = 0, opponentScore = 0;
    String currentScore;
    String startTime, endTime;
    int op = 0, tf = 0;
    int evaluationType, matchEvaluationTeamId;
    MatchSet ms;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

    /**
     * Creates new form PanEvaluation
     */
    public PanEvaluation(int setNum, int matchId, int teamEvaluateId, int opponentId, int evaluationType, int matchEvaluationTeamId) {
        initComponents();
        initializePlayer();
        playerList = teamDao.getTeamPlayers(teamEvaluateId);
        playerListOpp = teamDao.getTeamPlayers(opponentId);
//        panRallyList.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setNum = setNum;
        this.matchId = matchId;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;
        this.evaluationType = evaluationType;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
        initialPositionMap = new LinkedHashMap<>();
        rallyPositionMap = new LinkedHashMap<>();
        initialPositionMapOpp = new LinkedHashMap<>();
        rallyPositionMapOpp = new LinkedHashMap<>();
        substituePositionMap = new LinkedHashMap<>();

        ms = matchDao.getMatchSet(setNum, matchEvaluationTeamId);

//        List<Player> playerListL = teamDao.getTeamPlayers(matchEvaluationTeamId);
        for (Player p : playerList) {
            playerMap.put(p.getId(), p);
            ChestMap.put(p.getChestNo(), p);
        }

        for (Player p : playerListOpp) {
            playerMapOpp.put(p.getId(), p);
            ChestMapOpp.put(p.getChestNo(), p);
        }

        if (ms.getId() != 0) {
            Date date = new Date();
            this.matchEvaluationId = ms.getId();
            lblDate.setText(!ms.getDate().equals("") ? CommonUtil.ConvertDateFromDbToNormal(ms.getDate()) : formatter.format(date));
            lblTime.setText(!ms.getStart_time().equals("00:00") ? ms.getStart_time() : formatterTime.format(date));
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getEvaluationTeamId());
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getOpponentTeamId());
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
//            lblTimeOutEvalTeam.setText(evaluationName);
//            lblTimeOutOppTeam.setText(opponentName);
//            txtEvaluator.setText(ms.getEvaluator());
            lblScore.setText(ms.getHomeScore() + " - " + ms.getOpponentScore());
            homeScore = ms.getHomeScore();
            opponentScore = ms.getOpponentScore();
            op = ms.getOp();
            tf = ms.getTf();
            lblOp.setText("" + op);
            lblTf.setText("" + tf);
            lblWonBy.setText(Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getWon_by()));

            for (SetRotationOrder s : ms.getRotationOrder()) {
                initialPositionMap.put(s.getPosition(), playerMap.get(s.getPlayerId()));
            }

            if (initialPositionMap.size() > 6) {
                pos1.setText(initialPositionMap.get(1).getChestNo());
                pos2.setText(initialPositionMap.get(2).getChestNo());
                pos3.setText(initialPositionMap.get(3).getChestNo());
                pos4.setText(initialPositionMap.get(4).getChestNo());
                pos5.setText(initialPositionMap.get(5).getChestNo());
                pos6.setText(initialPositionMap.get(6).getChestNo());
//            libero.setText(initialPositionMap.get(7).getChestNo());
            }

            for (SetRotationOrder s : ms.getRotationOrderOpp()) {
                initialPositionMapOpp.put(s.getPosition(), playerMapOpp.get(s.getPlayerId()));
            }

            List<RallyEvaluation> rallies = rallyDao.getRalliesList(matchEvaluationId);

            totalRallies = rallies.size();
            rallyNumNext = totalRallies;
            for (RallyEvaluation rally : rallies) {
                cmbRallies.addItem(rally.getRallyNum());
                currentRally++;
            }
            rallyPositionMap = rallyDao.getLatestRallyRotationOrder(matchEvaluationId, ms.getEvaluationTeamId());
            rallyPositionMapOpp = rallyDao.getLatestRallyRotationOrderOpp(matchEvaluationId, ms.getOpponentTeamId());

            substituePositionMap.putAll(initialPositionMap);

            for (SetSubstitution s : ms.getSetSubstitutions()) {
                String cNo = s.getSubstitutePlayerId() == 0 ? "" : playerMap.get(s.getSubstitutePlayerId()).getChestNo();
                Player p = s.getSubstitutePlayerId() == 0 ? null : playerMap.get(s.getSubstitutePlayerId());
                if (!cNo.equals("")) {
                    if (s.getPoint2() == null) {
                        substituePositionMap.put(s.getPosition(), p);
                    }
                }
            }

        } else {

            Date date = new Date();
            lblDate.setText(formatter.format(date));
            lblTime.setText(formatterTime.format(date));
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(teamEvaluateId);
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(opponentId);
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
        }
    }

    public void initializePlayer() {
        panPlayer.setBackground(Color.BLACK);
        registerLibrary();
        final Canvas videoSurface = new Canvas();
        final List<String> vlcArgs = new ArrayList<String>();
        configureParameters(vlcArgs);
        final EmbeddedMediaPlayer mediaPlayer = createPlayer(vlcArgs, videoSurface);
        panPlayer.add(videoSurface, BorderLayout.CENTER);
//        mediaPlayer.playMedia("E:\\A Happy Child - CBSE Poem Class -1.mp4");
        PlayerControlsPanel p1 = new PlayerControlsPanel(mediaPlayer);
        panPlayer.add(p1, BorderLayout.SOUTH);
    }

    private void registerLibrary() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Volley-Ball\\PES-VB\\VLC\\VLC64");
        Native
                .loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class
                );
        LibXUtil.initialise();
    }

    /**
     * * Configure VLC parameters
     */
    private void configureParameters(final List<String> vlcArgs) {
        vlcArgs.add("--no-plugins-cache");
        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--no-snapshot-preview"); // Important, if this parameter would not be set on Windows, the app won't work
        if (RuntimeUtil.isWindows()) {
            vlcArgs.add("--plugin-path=D:\\vlc-2.2.1\\plugins");
        }
    }

    private EmbeddedMediaPlayer createPlayer(final List<String> vlcArgs, final Canvas videoSurface) {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");
        embeddedMediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        embeddedMediaPlayer.setPlaySubItems(true);
        return embeddedMediaPlayer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        panPlayer = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbRallies = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lblCurrentRally = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        pos4 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos2 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblOp = new javax.swing.JTextField();
        lblTf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblWonBy = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblevaluationName = new javax.swing.JLabel();
        lblopponentName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        panEvalRallyRow = new javax.swing.JPanel();
        lblStart = new javax.swing.JLabel();

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));
        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SERVICE");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        panPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panPlayer.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("RALLY :");

        cmbRallies.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbRallies.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        cmbRallies.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRalliesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRallies, 0, 87, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbRallies, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NEW");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel29.setBackground(new java.awt.Color(57, 74, 108));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108), 3));
        jPanel29.setLayout(new java.awt.GridLayout(1, 0));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrowback.png"))); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel29.add(jLabel18);

        lblCurrentRally.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCurrentRally.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentRally.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentRally.setText("RALLY :");
        jPanel29.add(lblCurrentRally);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrownext.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel29.add(jLabel20);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel43.setBackground(new java.awt.Color(57, 74, 108));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pos4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos4.setEnabled(false);

        pos3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos3.setEnabled(false);

        pos2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos2.setEnabled(false);

        pos5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos5.setEnabled(false);

        pos6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos6.setEnabled(false);

        pos1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos1.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Initial Rotation Order");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                        .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pos1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(pos2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel5.setBackground(new java.awt.Color(57, 74, 108));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Rotation Order");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(57, 74, 108));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Time Out");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(57, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Substitute");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(57, 74, 108));

        lblOp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblOp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblOp.setText("0");
        lblOp.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblOp.setEnabled(false);

        lblTf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblTf.setText("0");
        lblTf.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblTf.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TF -");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("OP +");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTf)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Date : ");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Time : ");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Score : ");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Won By : ");

        lblDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblDate.setText(" ");

        lblTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTime.setText(" ");

        lblScore.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblScore.setText(" ");

        lblWonBy.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblWonBy.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWonBy, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTime)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lblTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lblScore))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lblWonBy))))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(57, 74, 108));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Evaluating Team :");

        lblevaluationName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblevaluationName.setForeground(new java.awt.Color(255, 255, 255));
        lblevaluationName.setText(" ");

        lblopponentName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblopponentName.setForeground(new java.awt.Color(255, 255, 255));
        lblopponentName.setText(" ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Opponent Team :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblevaluationName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblopponentName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblopponentName)
                    .addComponent(jLabel6)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblevaluationName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108), 10));

        panEvalRallyRow.setBackground(new java.awt.Color(255, 255, 255));
        panEvalRallyRow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));
        panEvalRallyRow.setLayout(new java.awt.BorderLayout());

        lblStart.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStart.setText("START");
        lblStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStartMouseClicked(evt);
            }
        });
        panEvalRallyRow.add(lblStart, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panEvalRallyRow, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panEvalRallyRow, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPlayer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        setRotationDialog = new DialogPanEvaluationRotationOrder();
        setRotationDialog.init(this.teamEvaluateId, this.opponentId, this.matchId, lblevaluationName.getText(), lblopponentName.getText(), setNum, this.matchEvaluationTeamId);
        setRotationDialog.show();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void lblStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStartMouseClicked
        // TODO add your handling code here:
        if (this.matchEvaluationId == 0) {
            MatchSet ms = new MatchSet();
            ms.setMatchEvaluationTeamId(matchEvaluationTeamId);
            ms.setEvaluationTeamId(teamEvaluateId);
            ms.setOpponentTeamId(opponentId);
            ms.setStart_time(lblTime.getText());
            ms.setEvaluator("");
            ms.setSetNo(setNum);
            ms.setEnd_time("00:00");
            ms.setDate(CommonUtil.ConvertDateFromNormalToDB(lblDate.getText()));

            for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                SetRotationOrder sro = new SetRotationOrder();
                sro.setPosition(entry.getKey());
                sro.setPlayerId(entry.getValue().getId());
                ms.getRotationOrder().add(sro);
            }

            for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                SetSubstitution sro = new SetSubstitution();
                sro.setPosition(entry.getKey());
                sro.setRotation_player_id(entry.getValue().getId());
                ms.getSetSubstitutions().add(sro);
            }

            matchEvaluationId = matchDao.saveMatchSet(ms);
        }

        rallyNumNext++;
        panEvalRallyRow.removeAll();
        lblCurrentRally.setText("RALLY : " + rallyNumNext);
        pw = new PanEvaluationRally(teamEvaluateId, opponentId, rallyNumNext);
        panEvalRallyRow.add(pw, BorderLayout.CENTER);
        validate();
        repaint();
    }//GEN-LAST:event_lblStartMouseClicked

    private void cmbRalliesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRalliesItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = String.valueOf(evt.getItem());
            if (!evt.getItem().equals("Select")) {
                RallyEvaluation re = rallyDao.getRally(Integer.parseInt(item), matchEvaluationId, 0);
                panEvalRallyRow.removeAll();
                rallyNumNext = re.getRallyNum();
                lblCurrentRally.setText("RALLY : " + re.getRallyNum());
                pw = new PanEvaluationRally(re);
                panEvalRallyRow.add(pw, BorderLayout.CENTER);
                validate();
                repaint();
            }
        }

    }//GEN-LAST:event_cmbRalliesItemStateChanged

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        rallyNumNext = totalRallies + 1;
        cmbRallies.setSelectedItem("Select");
        lblCurrentRally.setText("RALLY : " + rallyNumNext);
        panEvalRallyRow.removeAll();
        pw = new PanEvaluationRally(teamEvaluateId, opponentId, rallyNumNext);
        panEvalRallyRow.add(pw, BorderLayout.CENTER);
        validate();
        repaint();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        cmbRallies.setSelectedItem("Select");
        rallyNumNext--;
        if (rallyNumNext > 0) {
            RallyEvaluation re = rallyDao.getRally(rallyNumNext, matchEvaluationId, 0);
            panEvalRallyRow.removeAll();
            rallyNumNext = re.getRallyNum();
            lblCurrentRally.setText("RALLY : " + re.getRallyNum());
            pw = new PanEvaluationRally(re);
            panEvalRallyRow.add(pw, BorderLayout.CENTER);
            validate();
            repaint();
        } else {
            rallyNumNext++;
            JOptionPane.showMessageDialog(panRallyCurrent, "Start Of the Rally");
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        cmbRallies.setSelectedItem("Select");
        rallyNumNext++;
        if (rallyNumNext <= totalRallies) {
            RallyEvaluation re = rallyDao.getRally(rallyNumNext, matchEvaluationId, 0);
            panEvalRallyRow.removeAll();
            rallyNumNext = re.getRallyNum();
            lblCurrentRally.setText("RALLY : " + re.getRallyNum());
            pw = new PanEvaluationRally(re);
            panEvalRallyRow.add(pw, BorderLayout.CENTER);
            validate();
            repaint();
        } else {
            rallyNumNext--;
            JOptionPane.showMessageDialog(panRallyCurrent, "End Of the Rally");
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        dialogEvaluationSubstitute = new DialogEvaluationSubstitute();
        dialogEvaluationSubstitute.init(setNum, matchEvaluationTeamId);
        dialogEvaluationSubstitute.show();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        dialogEvaluationTimeout = new DialogEvaluationTimeout();
        dialogEvaluationTimeout.init(setNum, matchEvaluationTeamId);
        dialogEvaluationTimeout.show();
    }//GEN-LAST:event_jPanel11MouseClicked

    public void setScore() {
        currentScore = homeScore + " - " + opponentScore;
        lblScore.setText(currentScore);
    }

    public void setScoreAfterUpdate() {
        MatchSet ms = matchDao.getMatchSet(setNum, matchEvaluationTeamId);
        lblScore.setText(ms.getHomeScore() + " - " + ms.getOpponentScore());
        homeScore = ms.getHomeScore();
        opponentScore = ms.getOpponentScore();
        op = ms.getOp();
        tf = ms.getTf();
        lblOp.setText("" + op);
        lblTf.setText("" + tf);
    }

    public void next() {

        cmbRallies.addItem(rallyNumNext);

        if (homeScore >= 25 || opponentScore >= 25) {
            List<Integer> arr = new ArrayList();
            arr.add(homeScore);
            arr.add(opponentScore);
            int max = Collections.max(arr);
            int min = Collections.min(arr);
            if ((max - min) >= 2) {
                if (max == homeScore) {
                    lblWonBy.setText(lblevaluationName.getText());
                    matchDao.updateMatchSetWonBy(teamEvaluateId, matchEvaluationId);
                } else {
                    lblWonBy.setText(lblopponentName.getText());
                    matchDao.updateMatchSetWonBy(opponentId, matchEvaluationId);
                }
                setScore();
                JOptionPane.showMessageDialog(Controller.panMatchSet, "Set Won By : " + lblWonBy.getText() + "\n Score is := " + homeScore + " : " + opponentScore);
                List<Integer> wonBy = matchDao.getSetNadWonBy(matchId);

                int team1WonBy = Collections.frequency(wonBy, teamEvaluateId);
                int team2wonBy = Collections.frequency(wonBy, opponentId);

                if (team1WonBy == 0 && team2wonBy >= 3) {
                    matchDao.updateMatchWonBy(opponentId, matchId);
                } else if (team2wonBy == 0 && team1WonBy >= 3) {
                    matchDao.updateMatchWonBy(teamEvaluateId, matchId);
                } else {
                    if (team2wonBy != 0 && team1WonBy != 0) {
                        if (team2wonBy >= 3) {
                            matchDao.updateMatchWonBy(opponentId, matchId);
                        } else if (team1WonBy >= 3) {
                            matchDao.updateMatchWonBy(teamEvaluateId, matchId);
                        }
                    }
                }

                Controller.panMatchSet.validate();
                Controller.panMatchSet.repaint();
            } else {
                setScore();
            }
        } else {
            setScore();
            totalRallies++;
            rallyNumNext++;
            lblCurrentRally.setText("RALLY : " + rallyNumNext);
            // TODO add your handling code here:
            panEvalRallyRow.removeAll();
//        ph = new PanEvaluationRallyHead(rallyNumNext, matchEvaluationId, rallyPositionMap, evaluationType);
            pw = new PanEvaluationRally(teamEvaluateId, opponentId, rallyNumNext);
//        panRallyEvalHead.add(ph, BorderLayout.CENTER);
            panEvalRallyRow.add(pw, BorderLayout.CENTER);
            validate();
            repaint();
        }

    }

    public void setRotations() {
        pos1.setText(initialPositionMap.get(1).getChestNo());
        pos2.setText(initialPositionMap.get(2).getChestNo());
        pos3.setText(initialPositionMap.get(3).getChestNo());
        pos4.setText(initialPositionMap.get(4).getChestNo());
        pos5.setText(initialPositionMap.get(5).getChestNo());
        pos6.setText(initialPositionMap.get(6).getChestNo());

        substituePositionMap.putAll(initialPositionMap);
//        libero.setText(initialPositionMap.get(7).getChestNo());

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbRallies;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblCurrentRally;
    private javax.swing.JLabel lblDate;
    public javax.swing.JTextField lblOp;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblStart;
    public javax.swing.JTextField lblTf;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblWonBy;
    private javax.swing.JLabel lblevaluationName;
    private javax.swing.JLabel lblopponentName;
    private javax.swing.JPanel panEvalRallyRow;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos2;
    private javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    // End of variables declaration//GEN-END:variables
}

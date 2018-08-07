/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.ArrowBean;
import com.vollyball.bean.PixelBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.VollyCourtCoordinateBean;
import com.vollyball.controller.Controller;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author nishant.vibhute
 */
public class PanVolleyCourt extends javax.swing.JPanel {

    LinkedHashMap<String, PixelBean> positionPixelValue = new LinkedHashMap<>();
    int x1, x2, y1, y2, x3, x4, y3, y4;
    private BufferedImage image;
    double phi;
    int barb;
    private Point2D[] curvePoints;
    boolean isEnd = false;
    List<VollyCourtCoordinateBean> vList = new ArrayList<>();
    List<ArrowBean> pathList = new ArrayList<>();
    Color color;
    VollyCourtCoordinateBean vmain;
    private Ellipse2D[] start;
    String from;

    /**
     * Creates new form PanVolleyCourt
     */
    public PanVolleyCourt() {
        initComponents();

        phi = Math.toRadians(40);
        barb = 20;

        setValuesOfPosition("H1", 212, 331);
        setValuesOfPosition("H2", 361, 331);
        setValuesOfPosition("H3", 361, 242);
        setValuesOfPosition("H4", 361, 155);
        setValuesOfPosition("H5", 212, 155);
        setValuesOfPosition("H6", 212, 242);
        setValuesOfPosition("SH1", 90, 331);
        setValuesOfPosition("SH6", 90, 242);
        setValuesOfPosition("SH5", 90, 155);

        setValuesOfPosition("O1", 611, 155);
        setValuesOfPosition("O2", 461, 155);
        setValuesOfPosition("O3", 461, 242);
        setValuesOfPosition("O4", 461, 331);
        setValuesOfPosition("O5", 611, 331);
        setValuesOfPosition("O6", 611, 242);

    }

    public void setValuesOfPosition(String pos, int x, int y) {
        PixelBean pb = new PixelBean();
        pb.setX(x);
        pb.setY(y);
        positionPixelValue.put(pos, pb);
    }

    public void repositionServer(String chestNum) {
        if (panHPos1.getText().equals(chestNum)) {
            panHPos1.setText("");
        } else if (panHPos5.getText().equals(chestNum)) {
            panHPos5.setText("");
        } else if (panHPos6.getText().equals(chestNum)) {
            panHPos6.setText("");
        }
    }

    public void setValues(List<VollyCourtCoordinateBean> vList, String from) {
        this.vList = vList;
        if (vList.size() > 0) {
            VollyCourtCoordinateBean v = vList.get(0);
            setValues(v, v.getChestNum(), from);
        }
    }

    public void setValues(VollyCourtCoordinateBean v, String chestNum, String from) {

        this.from = from;
        this.vmain = v;

        panOPos1.setText("1");
        panOPos2.setText("2");
        panOPos3.setText("3");
        panOPos4.setText("4");
        panOPos5.setText("5");
        panOPos6.setText("6");

        if (from.equalsIgnoreCase("report")) {
            panHPos1.setText("1");
            panHPos2.setText("2");
            panHPos3.setText("3");
            panHPos4.setText("4");
            panHPos5.setText("5");
            panHPos6.setText("6");
        } else {
            LinkedHashMap<Integer, Player> positionMap = Controller.panMatchSet.rallyPositionMap;
            panHPos1.setText(positionMap.get(1).getChestNo());
            panHPos2.setText(positionMap.get(2).getChestNo());
            panHPos3.setText(positionMap.get(3).getChestNo());
            panHPos4.setText(positionMap.get(4).getChestNo());
            panHPos5.setText(positionMap.get(5).getChestNo());
            panHPos6.setText(positionMap.get(6).getChestNo());
        }

        if (v.getSkill().equals("Service")) {
            if (v.getFrom() == 1) {
                panSHPos1.setText(chestNum);
                repositionServer(chestNum);
            } else if (v.getFrom() == 5) {
                panSHPos5.setText(chestNum);
                repositionServer(chestNum);
            } else if (v.getFrom() == 6) {
                panSHPos6.setText(chestNum);
                repositionServer(chestNum);
            }
        }

        this.x1 = v.getX1();
        this.y1 = v.getY1();
        this.x2 = v.getX2();
        this.y2 = v.getY2();

        this.x3 = v.getX3();
        this.y3 = v.getY3();
        this.x4 = v.getX4();
        this.y4 = v.getY4();
        color = v.getColor();
        curvePoints = new Point2D[]{new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), new Point(x4, y4)};
        marks = new Ellipse2D[curvePoints.length + 1];
        start = new Ellipse2D[1];
        for (int index = 0; index < marks.length; index++) {
            marks[index] = new Ellipse2D.Double();
        }
        start[0] = new Ellipse2D.Double();
        start[0].setFrame(x1 - 5, y1 - 5, 10, 10);
        animator.init();

        curvePoints = new Point2D[]{new Point2D.Double(curvePoints[0].getX(), curvePoints[0].getY()), new Point2D.Double(curvePoints[1].getX(), curvePoints[1].getY()), new Point2D.Double(curvePoints[2].getX(), curvePoints[2].getY()), new Point2D.Double(curvePoints[3].getX(), curvePoints[3].getY())};
        totalCurve = new Path2D.Double();
        totalCurve.moveTo(curvePoints[0].getX(), curvePoints[0].getY());
        totalCurve.curveTo(curvePoints[1].getX(), curvePoints[1].getY(), curvePoints[2].getX(), curvePoints[2].getY(), curvePoints[3].getX(), curvePoints[3].getY());

        try {
            image = ImageIO.read(new File("src\\com\\vollyball\\images\\vollycorntgreenNEW.png"));
        } catch (IOException ex) {
            Logger.getLogger(PanVolleyCourt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, this);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(new BasicStroke(3));
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        Point sw = new Point(x1, x2);
//        Point ne = new Point(y1, y2);
//        g2.setPaint(Color.GREEN);
//        g2.draw(new Line2D.Double(sw, ne));
//        g2.drawArc(100, 100, 100, 200, 90, 180);
//
//        try {
//            for (int i = 5; i <= 100; i++) {
//                g2.drawOval(i, 5, 1, 1);
//                Thread.sleep(10);
//                repaint();
//            }
//        } catch (Exception e) {
//
//        }
////        g2.draw(new Path2D.Float(Path2D.Float.curveTo(x1, y1, x1, y1, x2, y2) ));
////        drawArrowHead(g2, sw, ne, Color.red);

        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

        paintBezier(g);
        Point sw = new Point(x1, y1);
        Point ne = new Point(x2, y2);
//        g2.setPaint(Color.GREEN);
//        g2.draw(new Line2D.Double(sw, ne));
//        g2.drawArc(100, 100, 100, 200, 90, 180);
//
        if (isEnd) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setStroke(new BasicStroke(3));
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                    RenderingHints.VALUE_ANTIALIAS_ON);
//            drawArrowHead(g2, ne, sw, Color.blue);
        }
    }

    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for (int j = 0; j < 2; j++) {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            g2.setStroke(new BasicStroke(5));
            rho = theta - phi;
        }
    }
    private final Animator animator = new Animator();

    private Ellipse2D[] marks;
    private Path2D path;
    private Path2D totalCurve;

    private void paintBezier(Graphics g) {
        Path2D path1 = this.path;
        if (path1 != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (!from.equalsIgnoreCase("report")) {
                g2.setColor(Color.BLACK);
                for (Shape mark : start) {
                    g2.fill(mark);
                }
                g2.setStroke(new BasicStroke(5f));
                g2.setColor(Color.BLACK);
                for (Shape mark : start) {
                    g2.draw(mark);
                }
            }

//            g2.setStroke(new BasicStroke(0f));
//            g2.draw(totalCurve);
            if (pathList.size() > 0) {
                for (ArrowBean p : pathList) {
                    g2.setStroke(new BasicStroke(5f));
                    g2.setColor(p.getColor());
                    g2.draw(p.getPath());
                    Point sw = new Point(p.getX3(), p.getY3());
                    Point ne = new Point(p.getX4(), p.getY4());
                    drawArrowHead(g2, ne, sw, p.getColor());
                }
            }

            g2.setStroke(new BasicStroke(5f));
            g2.setColor(color);
            g2.draw(path1);
            g2.setStroke(new BasicStroke(.5f));
            g2.setColor(Color.BLACK);
            if (isEnd) {
                if (from.equalsIgnoreCase("report")) {
                    g2.setStroke(new BasicStroke(5f));
                    Point sw = new Point(x3, y3);
                    Point ne = new Point(x4, y4);
                    drawArrowHead(g2, ne, sw, color);
                }
            } else {
                g2.setColor(Color.YELLOW);
                for (Shape mark : marks) {
                    g2.fill(mark);
                }
                g2.setStroke(new BasicStroke(5f));
                g2.setColor(Color.BLUE);
                for (Shape mark : marks) {
                    g2.draw(mark);
                }
            }

        }
    }

    private class Animator implements ActionListener {

        private double distance = 0;
        private boolean moveTo = true;
        private ArrayList<Point2D> points = new ArrayList<Point2D>();
        private double step = -1;
        private double steps;
        private Timer timer = new Timer(0, this);
        int j = 1;
        int size;

        @Override
        public void actionPerformed(ActionEvent e) {
            step++;
            if (step <= steps) {
                double t = step / steps;
                Point2D newPoint = computeBezierPoint(new Point2D.Double(), t, curvePoints);
                marks[marks.length - 1].setFrame(newPoint.getX() - 10, newPoint.getY() - 10, 20, 20);
                points.add(newPoint);
                if (moveTo) {
                    path.moveTo(newPoint.getX(), newPoint.getY());
                    moveTo = false;
                } else {
                    path.lineTo(newPoint.getX(), newPoint.getY());
                }
                repaint();
            } else {
                timer.stop();

                if (j < size) {
                    ArrowBean a = new ArrowBean();
                    if (vmain.getSkill().contains("Set")) {
                        a.setX3(x3);
                        a.setY3(y3);
                    } else {
                        a.setX3(x1);
                        a.setY3(y1);
                    }
                    a.setX4(x4);
                    a.setY4(y4);
                    a.setPath(path);
                    a.setColor(color);
                    pathList.add(a);
                    VollyCourtCoordinateBean v = vList.get(j);
                    vmain = v;
                    x1 = v.getX1();
                    y1 = v.getY1();
                    x2 = v.getX2();
                    y2 = v.getY2();

                    x3 = v.getX3();
                    y3 = v.getY3();
                    x4 = v.getX4();
                    y4 = v.getY4();
                    curvePoints = new Point2D[]{new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), new Point(x4, y4)};
                    marks = new Ellipse2D[curvePoints.length + 1];
                    for (int index = 0; index < marks.length; index++) {
                        marks[index] = new Ellipse2D.Double();
                    }
                    color = v.getColor();
                    curvePoints = new Point2D[]{new Point2D.Double(curvePoints[0].getX(), curvePoints[0].getY()), new Point2D.Double(curvePoints[1].getX(), curvePoints[1].getY()), new Point2D.Double(curvePoints[2].getX(), curvePoints[2].getY()), new Point2D.Double(curvePoints[3].getX(), curvePoints[3].getY())};
                    totalCurve = new Path2D.Double();
                    totalCurve.moveTo(curvePoints[0].getX(), curvePoints[0].getY());
                    totalCurve.curveTo(curvePoints[1].getX(), curvePoints[1].getY(), curvePoints[2].getX(), curvePoints[2].getY(), curvePoints[3].getX(), curvePoints[3].getY());
                    timer.stop();
                    path = new Path2D.Double();
                    steps = 75;
                    step = -1;
                    moveTo = true;
                    j++;
                    int sleepTime = 31;
                    timer.setDelay(sleepTime);
                    timer.setInitialDelay(0);
                    timer.start();
                } else {
                    isEnd = true;
                }
                repaint();
            }
        }

        public void init() {
            timer.stop();
            if (vList.size() > 0) {
                size = vList.size();
            }
            steps = 75;
            step = -1;
            moveTo = true;
            path = new Path2D.Double();
            int sleepTime = 31;
            timer.setDelay(sleepTime);
            timer.setInitialDelay(0);
            timer.start();
        }

        private Point2D computeBezierPoint(Point2D rv, double t,
                Point2D... curve) {
            if (rv == null) {
                rv = new Point2D.Double();
            } else {
                rv.setLocation(0, 0);
            }
            int n = curve.length - 1;
            double oneMinusT = 1.0 - t;
            for (int index = 0; index < curve.length; index++) {
                double multiplier = index == 0 || index == n ? 1 : StrictMath.min(n - index, index) * n;
                multiplier *= StrictMath.pow(t, index) * StrictMath.pow(oneMinusT, n - index);
                rv.setLocation(rv.getX() + multiplier * curve[index].getX(), rv.getY() + multiplier * curve[index].getY());
            }
            return rv;
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
        jPanel10 = new javax.swing.JPanel();
        panHPos5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        panHPos6 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        panHPos1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        panHPos4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        panHPos3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        panHPos2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        panOPos2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        panOPos3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        panOPos4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        panOPos1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        panOPos6 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        panOPos5 = new javax.swing.JLabel();
        panSHPos5 = new javax.swing.JLabel();
        panSHPos1 = new javax.swing.JLabel();
        panSHPos6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(97, 153, 94));

        jPanel1.setBackground(new java.awt.Color(255, 102, 204));
        jPanel1.setOpaque(false);

        jPanel10.setBackground(new java.awt.Color(225, 155, 78));
        jPanel10.setOpaque(false);

        panHPos5.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos5.setForeground(new java.awt.Color(255, 255, 255));
        panHPos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(225, 155, 78));
        jPanel14.setOpaque(false);

        panHPos6.setBackground(new java.awt.Color(225, 155, 78));
        panHPos6.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos6.setForeground(new java.awt.Color(255, 255, 255));
        panHPos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(225, 155, 78));
        jPanel15.setOpaque(false);

        panHPos1.setBackground(new java.awt.Color(225, 155, 78));
        panHPos1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos1.setForeground(new java.awt.Color(255, 255, 255));
        panHPos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setOpaque(false);

        jPanel7.setBackground(new java.awt.Color(225, 155, 78));
        jPanel7.setOpaque(false);

        panHPos4.setBackground(new java.awt.Color(225, 155, 78));
        panHPos4.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos4.setForeground(new java.awt.Color(255, 255, 255));
        panHPos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(225, 155, 78));
        jPanel11.setOpaque(false);

        panHPos3.setBackground(new java.awt.Color(225, 155, 78));
        panHPos3.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos3.setForeground(new java.awt.Color(255, 255, 255));
        panHPos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(225, 155, 78));
        jPanel13.setOpaque(false);

        panHPos2.setBackground(new java.awt.Color(225, 155, 78));
        panHPos2.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panHPos2.setForeground(new java.awt.Color(255, 255, 255));
        panHPos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHPos2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setOpaque(false);

        panel.setBackground(new java.awt.Color(225, 155, 78));
        panel.setOpaque(false);

        panOPos2.setBackground(new java.awt.Color(225, 155, 78));
        panOPos2.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos2.setForeground(new java.awt.Color(255, 255, 255));
        panOPos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(225, 155, 78));
        jPanel17.setOpaque(false);

        panOPos3.setBackground(new java.awt.Color(225, 155, 78));
        panOPos3.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos3.setForeground(new java.awt.Color(255, 255, 255));
        panOPos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(225, 155, 78));
        jPanel18.setOpaque(false);

        panOPos4.setBackground(new java.awt.Color(225, 155, 78));
        panOPos4.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos4.setForeground(new java.awt.Color(255, 255, 255));
        panOPos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel5.setBackground(new java.awt.Color(255, 51, 153));
        jPanel5.setOpaque(false);

        jPanel19.setBackground(new java.awt.Color(225, 155, 78));
        jPanel19.setOpaque(false);

        panOPos1.setBackground(new java.awt.Color(225, 155, 78));
        panOPos1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos1.setForeground(new java.awt.Color(255, 255, 255));
        panOPos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(225, 155, 78));
        jPanel20.setOpaque(false);

        panOPos6.setBackground(new java.awt.Color(225, 155, 78));
        panOPos6.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos6.setForeground(new java.awt.Color(255, 255, 255));
        panOPos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(225, 155, 78));
        jPanel21.setOpaque(false);

        panOPos5.setBackground(new java.awt.Color(225, 155, 78));
        panOPos5.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        panOPos5.setForeground(new java.awt.Color(255, 255, 255));
        panOPos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panOPos5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panSHPos5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        panSHPos5.setForeground(new java.awt.Color(255, 255, 255));
        panSHPos5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        panSHPos1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        panSHPos1.setForeground(new java.awt.Color(255, 255, 255));
        panSHPos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        panSHPos6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        panSHPos6.setForeground(new java.awt.Color(255, 255, 255));
        panSHPos6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panSHPos5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(panSHPos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSHPos6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(panSHPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(panSHPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(panSHPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel panHPos1;
    private javax.swing.JLabel panHPos2;
    private javax.swing.JLabel panHPos3;
    private javax.swing.JLabel panHPos4;
    private javax.swing.JLabel panHPos5;
    private javax.swing.JLabel panHPos6;
    private javax.swing.JLabel panOPos1;
    private javax.swing.JLabel panOPos2;
    private javax.swing.JLabel panOPos3;
    private javax.swing.JLabel panOPos4;
    private javax.swing.JLabel panOPos5;
    private javax.swing.JLabel panOPos6;
    private javax.swing.JLabel panSHPos1;
    private javax.swing.JLabel panSHPos5;
    private javax.swing.JLabel panSHPos6;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}

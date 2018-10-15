/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.DigPanelLocation;
import com.vollyball.bean.DigPoints;
import com.vollyball.bean.DigTrianglePoints;
import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.enums.Skill;
import com.vollyball.util.CommonUtil;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class ImagePanel extends JPanel {

    Graphics g;
    private Image img;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    int midX = 0;
    int midY = 0;
    private Image imgHand;
    LinkedHashMap<String, JPanel> panGrid = new LinkedHashMap<String, JPanel>();
    LinkedHashMap<String, DigPanelLocation> panGridLocations = new LinkedHashMap<String, DigPanelLocation>();

    static LinkedHashMap<Integer, Point> homeChestNumShow = new LinkedHashMap<Integer, Point>();
    static LinkedHashMap<Integer, Point> oppChestNumShow = new LinkedHashMap<Integer, Point>();

    public LinkedHashMap<Integer, Player> rallyPositionMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<Integer, Player> rallyPositionMapOpp = new LinkedHashMap<Integer, Player>();

    boolean isTriangle = false;
    List<DigPoints> shapes = new ArrayList<>();

    public ImagePanel(String img, PanEvaluationRally p) {
        this(new ImageIcon(img).getImage(), p);

    }

    public void drawTriangle(List<DigTrianglePoints> points) {

        for (DigTrianglePoints p : points) {
            this.isTriangle = true;
            DigPoints dp = new DigPoints();
            JPanel p2 = panGrid.get(p.getToB());
            JPanel p1 = panGrid.get(p.getToA());
            JPanel p3 = panGrid.get(p.getFrom());

            DigPanelLocation dplP1 = panGridLocations.get(p.getToA());
            DigPanelLocation dplP2 = panGridLocations.get(p.getToB());
            DigPanelLocation dplP3 = panGridLocations.get(p.getFrom());

            dp.setX1((int) (((dplP1.getParentX() * 50) + (dplP1.getCurrentX() * p1.getWidth())) + (p1.getWidth() / 2)));
            dp.setY1((int) (((dplP1.getParentY() * 50) + (dplP1.getCurrentY() * p1.getHeight())) + (p1.getHeight() / 2)));
            dp.setX2((int) (((dplP2.getParentX() * 50) + (dplP2.getCurrentX() * p2.getWidth())) + (p2.getWidth() / 2)));
            dp.setY2((int) (((dplP2.getParentY() * 50) + (dplP2.getCurrentY() * p2.getHeight())) + (p2.getHeight() / 2)));
            dp.setMidx((int) (((dplP3.getParentX() * 50) + (dplP3.getCurrentX() * p3.getWidth())) + (p3.getWidth())));
            dp.setMidy((int) (((dplP3.getParentY() * 50) + (dplP3.getCurrentY() * p3.getHeight())) + (p3.getHeight())));
            dp.setColor(p.getColor());
            shapes.add(dp);
        }
        repaint();
    }

    public ImagePanel(Image img, PanEvaluationRally p) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(new GridLayout(8, 5));

        if (p != null) {
            rallyPositionMap.putAll(p.rallyPositionMap);
            rallyPositionMapOpp.putAll(p.rallyPositionMapOpp);
        }
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 5; j++) {

                if (i == 1) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "R4";
                    } else if (j == 2) {
                        panCode = panCode + "12";
                    } else if (j == 3) {
                        panCode = panCode + "62";
                    } else if (j == 4) {
                        panCode = panCode + "52";
                    } else if (j == 5) {
                        panCode = panCode + "L4";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 2) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "R3";
                    } else if (j == 2) {
                        panCode = panCode + "1";
                    } else if (j == 3) {
                        panCode = panCode + "6";
                    } else if (j == 4) {
                        panCode = panCode + "5";
                    } else if (j == 5) {
                        panCode = panCode + "L3";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 3) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "R2";
                    } else if (j == 2) {
                        panCode = panCode + "11";
                    } else if (j == 3) {
                        panCode = panCode + "61";
                    } else if (j == 4) {
                        panCode = panCode + "51";
                    } else if (j == 5) {
                        panCode = panCode + "L2";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 4) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "R1";
                    } else if (j == 2) {
                        panCode = panCode + "2";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "4";
                    } else if (j == 5) {
                        panCode = panCode + "L1";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 5) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L1";
                    } else if (j == 2) {
                        panCode = panCode + "4";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "2";
                    } else if (j == 5) {
                        panCode = panCode + "R1";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 6) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L2";
                    } else if (j == 2) {
                        panCode = panCode + "51";
                    } else if (j == 3) {
                        panCode = panCode + "61";
                    } else if (j == 4) {
                        panCode = panCode + "11";
                    } else if (j == 5) {
                        panCode = panCode + "R2";
                    }
                    addPanel(panCode, i, j);
                }

                if (i == 7) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L3";
                    } else if (j == 2) {
                        panCode = panCode + "5";
                    } else if (j == 3) {
                        panCode = panCode + "6";
                    } else if (j == 4) {
                        panCode = panCode + "1";
                    } else if (j == 5) {
                        panCode = panCode + "R3";
                    }
                    addPanel(panCode, i, j);

                }

                if (i == 8) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L4";
                    } else if (j == 2) {
                        panCode = panCode + "52";
                    } else if (j == 3) {
                        panCode = panCode + "62";
                    } else if (j == 4) {
                        panCode = panCode + "12";
                    } else if (j == 5) {
                        panCode = panCode + "R4";
                    }
                    addPanel(panCode, i, j);
                }

            }
        }

    }

    public void addPanel(String panCode, int parenty, int parentx) {
        JPanel p = new JPanel();
        p.setSize(50, 50);
        p.setLayout(new GridLayout(2, 2));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createDashedBorder(new Color(150, 222, 235), 1, 10));
        String code = panCode;
        for (int k = 1; k <= 2; k++) {
            for (int l = 1; l <= 2; l++) {

                if (k == 1) {
                    if (l == 1) {
                        code = panCode + "A";
                    } else {
                        code = panCode + "B";
                    }
                }
                if (k == 2) {
                    if (l == 1) {
                        code = panCode + "D";
                    } else {
                        code = panCode + "C";
                    }
                }

                DigPanelLocation dpl = new DigPanelLocation();
                dpl.setParentX(parentx - 1);
                dpl.setParentY(parenty - 1);
                dpl.setCurrentX(k - 1);
                dpl.setCurrentY(l - 1);
                JPanel pin = new JPanel();
                pin.setSize(25, 25);
                pin.setBorder(BorderFactory.createDashedBorder(new Color(150, 222, 235), 1, 10));
                pin.setOpaque(false);
                p.add(pin);
                panGrid.put(code, pin);
                panGridLocations.put(code, dpl);
            }
        }
        this.add(p);
    }

    public void showPlayerChestNum() {

        homeChestNumShow.put(1, getPoint("H1A"));
        homeChestNumShow.put(2, getPoint("H2A"));
        homeChestNumShow.put(3, getPoint("H3A"));
        homeChestNumShow.put(4, getPoint("H4A"));
        homeChestNumShow.put(5, getPoint("H5A"));
        homeChestNumShow.put(6, getPoint("H6A"));

        oppChestNumShow.put(1, getPoint("O1A"));
        oppChestNumShow.put(2, getPoint("O2A"));
        oppChestNumShow.put(3, getPoint("O3A"));
        oppChestNumShow.put(4, getPoint("O4A"));
        oppChestNumShow.put(5, getPoint("O5A"));
        oppChestNumShow.put(6, getPoint("O6A"));

    }

    public static DigPoints getPoints(String pan1, String pan2) {
        DigPoints dp = new DigPoints();
//        JPanel p1 = panGrid.get(pan1);
//        JPanel p2 = panGrid.get(pan2);
//        dp.setX1((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
//        dp.setY1((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
//        dp.setX2((int) (p2.getParent().getLocation().getX() + p2.getLocation().getX() + (p2.getWidth() / 2)));
//        dp.setY2((int) (p2.getParent().getLocation().getY() + p2.getLocation().getY() + (p2.getHeight() / 2)));
        return dp;
    }

    public static Point getPlayerPoints(String chestNo) {

        Point p = null;
        int centerX, centerY;

        for (Map.Entry<Integer, Player> entry : Controller.panEvaluationRally.rallyPositionMap.entrySet()) {
            Player player = entry.getValue();
            String text = player.getChestNo();
            if (text.equals(chestNo)) {
                Point p1 = homeChestNumShow.get(entry.getKey());
                centerX = (int) p1.getX();
                centerY = (int) p1.getY();
                p = new Point(centerX, centerY);
            }
        }

        return p;
    }

    public Point getPoint(String pan) {
        JPanel p1 = panGrid.get(pan);
        int x = (int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth()));
        int y = (int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight()));
        Point p = new Point(x, y);
        return p;
    }

    public void dig(String skill, List<String> panel, int tempo) {
        repaint();
        int k = 0;
        List<String> panels = new ArrayList<>(panel);
        String playerNum = "";
        int playerPos = -1;
        String homeCourt = "";
        boolean isHomeFound = false;
        for (int i = 0; i < panels.size(); i++) {
            if (CommonUtil.isNumeric(panels.get(i))) {
                playerNum = panels.get(i);
                playerPos = i;
            } else {
                if (panels.get(i).startsWith("H") && !isHomeFound) {
                    homeCourt = panels.get(i);
                    isHomeFound = true;
                }
            }

        }

        if (playerPos != -1) {
            panels.remove(playerPos);
        }

        if (panels.size() > 1) {
            JPanel p1 = panGrid.get(panels.get(0));
            for (int i = 1; i < panels.size(); i++) {
                DigPoints dp = new DigPoints();
                JPanel p2 = panGrid.get(panels.get(i));
                dp.setX1((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
                dp.setY1((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
                dp.setX2((int) (p2.getParent().getLocation().getX() + p2.getLocation().getX() + (p2.getWidth() / 2)));
                dp.setY2((int) (p2.getParent().getLocation().getY() + p2.getLocation().getY() + (p2.getHeight() / 2)));
                if (skill.equalsIgnoreCase("Set")) {
                    Point p = setCurve(dp, tempo);
                    dp.setMidx((int) p.getX());
                    dp.setMidy((int) p.getY());
                } else {
                    dp.setMidx(dp.getX2());
                    dp.setMidy(dp.getY2());
                }
                shapes.add(dp);
                p1 = panGrid.get(panels.get(i));
            }
        }

        if (!playerNum.isEmpty() && isHomeFound) {
            Point p = getPlayerPoints(playerNum);
            DigPoints dp = new DigPoints();
            dp.setX1((int) p.getX());
            dp.setY1((int) p.getY());
            dp.setMidx(dp.getX1());
            dp.setMidy(dp.getY1());

            if (skill.equals(Skill.Set.getType())) {
                JPanel p1 = panGrid.get("H2D");
                dp.setPlayerMoved(1);
                dp.setX2((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
                dp.setY2((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
                shapes.add(dp);

                DigPoints dpNew = new DigPoints();
                dp.setPlayerMoved(2);
                dpNew.setX1((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
                dpNew.setY1((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
                dpNew.setMidx(dpNew.getX1());
                dpNew.setMidy(dpNew.getY1());
                JPanel p2 = panGrid.get(homeCourt);
                dpNew.setPlayerMoved(1);
                dpNew.setX2((int) (p2.getParent().getLocation().getX() + p2.getLocation().getX() + (p2.getWidth() / 2)));
                dpNew.setY2((int) (p2.getParent().getLocation().getY() + p2.getLocation().getY() + (p2.getHeight() / 2)));
                shapes.add(dpNew);

            } else {
                JPanel p1 = panGrid.get(homeCourt);
                dp.setPlayerMoved(1);
                dp.setX2((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
                dp.setY2((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
                shapes.add(dp);
            }
        }

        repaint();
    }

    public Point setCurve(DigPoints dp, int tempo) {
        Point p = new Point();
        int val = tempo;
        int x1 = dp.getX1();
        int y1 = dp.getY1();
        int x2 = dp.getX2();
        int y2 = dp.getY2();
        Shape s = new Line2D.Double(x1, y1, x2, y2);

        int xlinecenter = (int) s.getBounds().getCenterX();
        int ylinecenter = (int) s.getBounds().getCenterY();

        if ((x2 - x1) == 0) {
            midX = x1 < 150 ? (x1 + val) : (x1 - val);
            midY = ylinecenter;
        } else {
            double slope = (double) (y2 - y1) / (x2 - x1);
            slope = Math.abs(slope);
            if (slope == 0) {
                midX = xlinecenter;
                midY = y1 - val;
            } else {
                slope = Math.ceil(slope);
                int yval = (int) ((int) val / slope);
                int xval = (int) ((int) val / slope);

                midX = xlinecenter + val;
                midY = ylinecenter - yval;

                int xdirection = x2 - x1;
                int ydirection = y2 - y1;

                if (xdirection < 0 && ydirection > 0) {
                    midX = xlinecenter + xval;
                    midY = ylinecenter + val;
                } else if (xdirection > 0 && ydirection < 0) {
                    midX = xlinecenter - val;
                    midY = ylinecenter - val;
                } else if (xdirection < 0 && ydirection < 0) {
                    midX = xlinecenter + val;
                    midY = ylinecenter - yval;
                }
            }
        }
        p.setLocation(midX, midY);
        return p;
    }

    public void refresh() {
        repaint();
    }

    public void setPlayerPosition(LinkedHashMap<Integer, Player> rallyPositionMap, LinkedHashMap<Integer, Player> rallyPositionMapOpp) {
        this.rallyPositionMap = rallyPositionMap;
        this.rallyPositionMapOpp = rallyPositionMapOpp;
        repaint();
    }

    public void drawImage(int x1, int y1, int x2, int y2) {
        this.img = img;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        repaint();
    }

    public void paintComponent(Graphics g) {

        if (!isTriangle) {
            showPlayerChestNum();
            g.drawImage(img, 0, 0, null);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (Map.Entry<Integer, Player> entry : rallyPositionMap.entrySet()) {

                Point p = homeChestNumShow.get(entry.getKey());
                Player player = entry.getValue();
                String text = player.getChestNo();
                int centerX = (int) p.getX(), centerY = (int) p.getY();
                int ovalWidth = 30, ovalHeight = 30;

                // Draw oval
                GradientPaint redtowhite = new GradientPaint(centerX - ovalWidth / 2, centerY - ovalHeight / 2, PlayerPosition.getNameById(player.getPosition()).getColor(), centerX - ovalWidth / 2 + 60, centerY - ovalHeight / 2, Color.white);
                g2.setPaint(redtowhite);
                g2.fill(new Ellipse2D.Double(centerX - ovalWidth / 2, centerY - ovalHeight / 2, 30, 30));
                g2.setPaint(Color.black);

                Font font = new Font("Serif", Font.BOLD, 20);
                g2.setFont(font);
                // Draw centered text
                FontMetrics fm = g.getFontMetrics();
                double textWidth = fm.getStringBounds(text, g).getWidth();

                g.setColor(Color.WHITE);
                g.drawString(text, (int) (centerX - textWidth / 2),
                        (int) (centerY + fm.getMaxAscent() / 3));
            }

            for (Map.Entry<Integer, Player> entry : rallyPositionMapOpp.entrySet()) {
                Point p = oppChestNumShow.get(entry.getKey());
                Player player = entry.getValue();
                String text = player.getChestNo();
                int centerX = (int) p.getX(), centerY = (int) p.getY();
                int ovalWidth = 30, ovalHeight = 30;

                // Draw oval
                GradientPaint redtowhite = new GradientPaint(centerX - ovalWidth / 2, centerY - ovalHeight / 2, PlayerPosition.getNameById(player.getPosition()).getColor(), centerX - ovalWidth / 2 + 60, centerY - ovalHeight / 2, Color.white);
                g2.setPaint(redtowhite);
                g2.fill(new Ellipse2D.Double(centerX - ovalWidth / 2, centerY - ovalHeight / 2, 30, 30));
                g2.setPaint(Color.black);

                // Draw centered text
                FontMetrics fm = g.getFontMetrics();
                double textWidth = fm.getStringBounds(text, g).getWidth();
                Font font = new Font("Serif", Font.BOLD, 20);
                g2.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString(text, (int) (centerX - textWidth / 2),
                        (int) (centerY + fm.getMaxAscent() / 3));
            }

            for (DigPoints dp : shapes) {
                Path2D p = new GeneralPath();
                p.moveTo(dp.getX1(), dp.getY1());
                p.curveTo(dp.getX1(), dp.getY1(), dp.getMidx(), dp.getMidy(), dp.getX2(), dp.getY2());

                if (dp.getPlayerMoved() == 1) {
                    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 5);
                    g2.setColor(Color.blue);
                    g2.setStroke(dashed);
                    imgHand = new ImageIcon("src\\com\\vollyball\\images\\153657649317869378.png").getImage();
                    g.drawImage(imgHand, dp.getX2() - 15, dp.getY2() - 15, null);
//                imgHand = new ImageIcon("src\\com\\vollyball\\images\\153657649317869378 (1).png").getImage();
//                g.drawImage(imgHand, dp.getX1() - 15, dp.getY1() - 15, null);
                } else if (dp.getPlayerMoved() == 2) {
                    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{10}, 5);
                    g2.setColor(Color.BLACK);
                    g2.setStroke(dashed);
                    g.setColor(Color.RED);
                    g.fillOval(dp.getX2() - 5, dp.getY2() - 5, 10, 10);
                } else {
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(3));

                    Point sw = new Point(dp.getMidx(), dp.getMidy());
                    Point ne = new Point(dp.getX1(), dp.getY1());

                    drawArrowHead(g2, sw, ne, Color.WHITE);
                }
                g2.draw(p);
            }
        } else {
            g.drawImage(img, 0, 0, null);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int n = 3;
            int x[] = new int[n];
            int y[] = new int[n];

            for (DigPoints dp : shapes) {
                x[0] = dp.getX1();
                x[1] = dp.getMidx();
                x[2] = dp.getX2();
                y[0] = dp.getY1();
                y[1] = dp.getMidy();
                y[2] = dp.getY2();

                Polygon p = new Polygon(x, y, n);  // This polygon represents a triangle with the above
                //   vertices.
                g.setColor(dp.getColor());
                g.fillPolygon(p);
            }
        }

    }

    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {
        double phi;
        int barb;
        phi = Math.toRadians(40);
        barb = 20;
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
            g2.setStroke(new BasicStroke(3));
            rho = theta - phi;
        }
    }

}

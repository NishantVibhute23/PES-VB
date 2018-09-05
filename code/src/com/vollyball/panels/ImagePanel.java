/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.DigPoints;
import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.enums.PlayerPosition;
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
import java.awt.RenderingHints;
import java.awt.Shape;
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
    static LinkedHashMap<String, JPanel> panGrid = new LinkedHashMap<String, JPanel>();

    static LinkedHashMap<Integer, Point> homeChestNumShow = new LinkedHashMap<Integer, Point>();
    static LinkedHashMap<Integer, Point> oppChestNumShow = new LinkedHashMap<Integer, Point>();

    List<DigPoints> shapes = new ArrayList<>();

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());

    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(new GridLayout(8, 5));

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
                    addPanel(panCode);
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
                    addPanel(panCode);
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
                    addPanel(panCode);
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
                    addPanel(panCode);
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
                    addPanel(panCode);
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
                    addPanel(panCode);
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
                    addPanel(panCode);

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
                    addPanel(panCode);
                }

            }
        }

    }

    public void addPanel(String panCode) {
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

                JPanel pin = new JPanel();
                pin.setSize(25, 25);
                pin.setBorder(BorderFactory.createDashedBorder(new Color(150, 222, 235), 1, 10));
                pin.setOpaque(false);
                p.add(pin);
                panGrid.put(code, pin);
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
        JPanel p1 = panGrid.get(pan1);
        JPanel p2 = panGrid.get(pan2);
        dp.setX1((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
        dp.setY1((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
        dp.setX2((int) (p2.getParent().getLocation().getX() + p2.getLocation().getX() + (p2.getWidth() / 2)));
        dp.setY2((int) (p2.getParent().getLocation().getY() + p2.getLocation().getY() + (p2.getHeight() / 2)));
        return dp;
    }

    public static Point getPlayerPoints(String chestNo) {

        Point p = null;
        int centerX, centerY;

        for (Map.Entry<Integer, Player> entry : Controller.panMatchSet.rallyPositionMap.entrySet()) {
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
        int y = (int) ((p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight())));
        Point p = new Point(x, y);
        return p;
    }

    public void dig(String skill, List<String> panels, int tempo) {
        repaint();
        int k = 0;
        if (CommonUtil.isNumeric(panels.get(0))) {
            Point p = getPlayerPoints(panels.get(0));
            DigPoints dp = new DigPoints();
            dp.setX1((int) p.getX());
            dp.setY1((int) p.getY());
            dp.setMidx(dp.getX1());
            dp.setMidy(dp.getY1());
            JPanel p1 = panGrid.get(panels.get(1));
            dp.setX2((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
            dp.setY2((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
            shapes.add(dp);
            k++;
        }

        JPanel p1 = panGrid.get(panels.get(k));
        for (int i = k + 1; i < panels.size(); i++) {
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
                dp.setMidx(dp.getX1());
                dp.setMidy(dp.getY1());
            }
            shapes.add(dp);
            p1 = panGrid.get(panels.get(i));
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

    public void drawImage(int x1, int y1, int x2, int y2) {
        this.img = img;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        repaint();
    }

    public void paintComponent(Graphics g) {
        showPlayerChestNum();
        g.drawImage(img, 0, 0, null);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Map.Entry<Integer, Player> entry : Controller.panMatchSet.rallyPositionMap.entrySet()) {

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

        for (Map.Entry<Integer, Player> entry : Controller.panMatchSet.rallyPositionMapOpp.entrySet()) {
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
            g2.draw(p);
        }

    }

}

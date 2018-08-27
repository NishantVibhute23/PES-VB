/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.DigPoints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
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
class ImagePanel extends JPanel {

    Graphics g;
    private Image img;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    int midX = 0;
    int midY = 0;
    LinkedHashMap<String, JPanel> panGrid = new LinkedHashMap<String, JPanel>();

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
                    String panCode = "OB";
                    if (j == 1) {
                        panCode = panCode + "1";
                    } else if (j == 2) {
                        panCode = panCode + "2";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "4";
                    } else if (j == 5) {
                        panCode = panCode + "5";
                    }
                    addPanel(panCode);
                }

                if (i == 2) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L3";
                    } else if (j == 2) {
                        panCode = panCode + "7";
                    } else if (j == 3) {
                        panCode = panCode + "8";
                    } else if (j == 4) {
                        panCode = panCode + "9";
                    } else if (j == 5) {
                        panCode = panCode + "R3";
                    }
                    addPanel(panCode);
                }

                if (i == 3) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L2";
                    } else if (j == 2) {
                        panCode = panCode + "4";
                    } else if (j == 3) {
                        panCode = panCode + "5";
                    } else if (j == 4) {
                        panCode = panCode + "6";
                    } else if (j == 5) {
                        panCode = panCode + "R2";
                    }
                    addPanel(panCode);
                }

                if (i == 4) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L1";
                    } else if (j == 2) {
                        panCode = panCode + "1";
                    } else if (j == 3) {
                        panCode = panCode + "2";
                    } else if (j == 4) {
                        panCode = panCode + "3";
                    } else if (j == 5) {
                        panCode = panCode + "R1";
                    }
                    addPanel(panCode);
                }

                if (i == 5) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L1";
                    } else if (j == 2) {
                        panCode = panCode + "1";
                    } else if (j == 3) {
                        panCode = panCode + "2";
                    } else if (j == 4) {
                        panCode = panCode + "3";
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
                        panCode = panCode + "4";
                    } else if (j == 3) {
                        panCode = panCode + "5";
                    } else if (j == 4) {
                        panCode = panCode + "6";
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
                        panCode = panCode + "7";
                    } else if (j == 3) {
                        panCode = panCode + "8";
                    } else if (j == 4) {
                        panCode = panCode + "9";
                    } else if (j == 5) {
                        panCode = panCode + "R3";
                    }
                    addPanel(panCode);

                }

                if (i == 8) {
                    String panCode = "HB";
                    if (j == 1) {
                        panCode = panCode + "1";
                    } else if (j == 2) {
                        panCode = panCode + "2";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "4";
                    } else if (j == 5) {
                        panCode = panCode + "5";
                    }
                    addPanel(panCode);
                }

            }
        }
        for (Map.Entry<String, JPanel> entry : panGrid.entrySet()) {
            String string = entry.getKey();
            System.out.println(string);
        }
    }

    public void addPanel(String panCode) {
        JPanel p = new JPanel();
        p.setSize(66, 66);
        p.setLayout(new GridLayout(2, 2));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createDashedBorder(Color.pink));
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
                        code = panCode + "C";
                    } else {
                        code = panCode + "D";
                    }
                }

                JPanel pin = new JPanel();
                pin.setSize(33, 33);
                pin.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY));
                pin.setOpaque(false);
                p.add(pin);
                panGrid.put(code, pin);
            }
        }
        this.add(p);
    }

    public void dig(String skill, List<String> panels) {
        repaint();
        JPanel p1 = panGrid.get(panels.get(0));
        for (int i = 1; i < panels.size(); i++) {
            DigPoints dp = new DigPoints();
            JPanel p2 = panGrid.get(panels.get(i));

            dp.setX1((int) (p1.getParent().getLocation().getX() + p1.getLocation().getX() + (p1.getWidth() / 2)));
            dp.setY1((int) (p1.getParent().getLocation().getY() + p1.getLocation().getY() + (p1.getHeight() / 2)));
            dp.setX2((int) (p2.getParent().getLocation().getX() + p2.getLocation().getX() + (p2.getWidth() / 2)));
            dp.setY2((int) (p2.getParent().getLocation().getY() + p2.getLocation().getY() + (p2.getHeight() / 2)));

            if (skill.equalsIgnoreCase("Set")) {
                Point p = setCurve(dp);
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

    public Point setCurve(DigPoints dp) {
        Point p = new Point();
        int val = 60;
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
//                if (xlinecenter < 150) {
                midX = xlinecenter + val;
                midY = ylinecenter - yval;
//                } else {
//                    midX = xlinecenter - val;
//                    midY = ylinecenter - yval;
//                }

            }
        }
        p.setLocation(midX, midY);
        return p;
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

        g.drawImage(img, 0, 0, null);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (DigPoints dp : shapes) {
            Path2D p = new GeneralPath();
            p.moveTo(dp.getX1(), dp.getY1());
            p.curveTo(dp.getX1(), dp.getY1(), dp.getMidx(), dp.getMidy(), dp.getX2(), dp.getY2());
            g2.draw(p);
        }

    }

}

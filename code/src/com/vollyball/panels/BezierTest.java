/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

/**
 *
 * @author nishant.vibhute
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BezierTest {

    private class Animator implements ActionListener {

        private double distance = 0;
        private boolean moveTo = true;
        private ArrayList<Point2D> points = new ArrayList<Point2D>();
        private double step = -1;
        private double steps;
        private Timer timer = new Timer(0, this);

        @Override
        public void actionPerformed(ActionEvent e) {
            step++;
            if (step <= steps) {
                double t = step / steps;
                Point2D newPoint = computeBezierPoint(new Point2D.Double(), t, curvePoints);
                marks[marks.length - 1].setFrame(newPoint.getX() - 5, newPoint.getY() - 5, 10, 10);
                points.add(newPoint);
                if (moveTo) {
                    path.moveTo(newPoint.getX(), newPoint.getY());
                    moveTo = false;
                } else {
                    path.lineTo(newPoint.getX(), newPoint.getY());
                }
                demoComponent.repaint();
            } else {
                timer.stop();
                animationButton.setEnabled(true);
                if (distance > 0d) {
                    System.out.println("Maximum difference " + distance);
                }
            }
        }

        public void init() {
            timer.stop();
            animationButton.setEnabled(false);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new BezierTest().createGUI();
            }
        });
    }
    private final JButton animationButton = new JButton(new AbstractAction("Start animation") {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            animator.init();
        }
    });
    private final Animator animator = new Animator();
    private Point2D[] curvePoints = new Point2D[]{new Point(127, 215), new Point(295, 183), new Point(515, 139), new Point(596, 122)};
    private JComponent demoComponent = new JComponent() {

        private static final long serialVersionUID = 1L;

        {
            setPreferredSize(new Dimension(400, 400));
            addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    int w = getWidth();
                    int h = getHeight();
                    recalculateAfterResize(w, h);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (isVisible()) {

                super.paintComponent(g);
                paintBezier(g);
            }
        }
    };

    private final Ellipse2D[] marks;
    private Path2D path;
    private Path2D totalCurve;
    private BufferedImage image;

    {
        marks = new Ellipse2D[curvePoints.length + 1];
        for (int index = 0; index < marks.length; index++) {
            marks[index] = new Ellipse2D.Double();
        }

        try {
            image = ImageIO.read(new File("src\\com\\vollyball\\images\\vollycorntgreenNEW.png"));
        } catch (IOException ex) {
            Logger.getLogger(PanVolleyCourt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(demoComponent, BorderLayout.CENTER);
        frame.pack();
        frame.setLocation(150, 150);
        frame.setVisible(true);
    }

    private void paintBezier(Graphics g) {
        Path2D path1 = this.path;
        if (path1 != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GREEN);
            for (Shape mark : marks) {
                g2.fill(mark);
            }
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(Color.BLACK);
            for (Shape mark : marks) {
                g2.draw(mark);
            }
            g2.setStroke(new BasicStroke(0f));
            g2.draw(totalCurve);
            g2.setStroke(new BasicStroke(1f));
            g2.setColor(Color.RED);
            g2.draw(path1);
            g2.setStroke(new BasicStroke(.5f));
            g2.setColor(Color.BLACK);
        }
    }

    private void recalculateAfterResize(int w, int h) {
        curvePoints = new Point2D[]{new Point2D.Double(curvePoints[0].getX(), curvePoints[0].getY()), new Point2D.Double(curvePoints[1].getX(), curvePoints[1].getY()), new Point2D.Double(curvePoints[2].getX(), curvePoints[2].getY()), new Point2D.Double(curvePoints[3].getX(), curvePoints[3].getY())};
        totalCurve = new Path2D.Double();
        totalCurve.moveTo(curvePoints[0].getX(), curvePoints[0].getY());
        totalCurve.curveTo(curvePoints[1].getX(), curvePoints[1].getY(), curvePoints[2].getX(), curvePoints[2].getY(), curvePoints[3].getX(), curvePoints[3].getY());
        animator.init();
    }
}

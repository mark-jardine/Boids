package Boids;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class AppPanel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 1100;
    public final static int PANEL_HEIGHT = 750;
    public final static int NUM_BOIDS = 100;
    ArrayList<Boid> boids = Boid.spawn(NUM_BOIDS);
    Timer timer;


    public AppPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING
                , RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        //g2d.draw(new Ellipse2D.Double(20,20,30,30));


        for (Boid b : boids) {
            Ellipse2D.Double boidShape = new Ellipse2D.Double(b.getxPos(), b.getyPos(), 30, 30);
            g2d.draw(boidShape);
            g2d.setPaint(b.getColour());
            g2d.fill(boidShape);
            b.move();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

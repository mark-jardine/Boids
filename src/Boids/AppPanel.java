package Boids;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Timer;


public class AppPanel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 1100;
    public final static int PANEL_HEIGHT = 750;
    public final static int NUM_BOIDS = 10;
    ArrayList<Boid> boids = Boid.spawn(NUM_BOIDS);
    Timer timer;
    boolean running = false;


    public AppPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING
                , RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        //g2d.draw(new Ellipse2D.Double(20,20,30,30));


        try {
            for (Boid b : boids) {
                Ellipse2D.Double boidShape = new Ellipse2D.Double(b.getxPos(), b.getyPos(), 30, 30);
                g2d.draw(boidShape);
                g2d.fill(boidShape);
                b.move();
            }
        } catch (NullPointerException n) {
            System.out.println(n.getMessage());
        }
        repaint();
    }


    private void init_Sim() {
        running = true;
        timer = new Timer();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

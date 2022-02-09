package Boids;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppPanel extends JPanel implements ActionListener {
    public final static int PANEL_WIDTH = 1100;
    public final static int PANEL_HEIGHT = 750;

    public AppPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING
                , RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

    }

    public void draw(Graphics g) {

    }

    private void init_Boids(){


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

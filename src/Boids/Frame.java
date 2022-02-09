package Boids;

import javax.swing.JFrame;

public class Frame extends JFrame {

    public Frame() {
        this.add(new AppPanel());

        this.setTitle("Boids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }


}

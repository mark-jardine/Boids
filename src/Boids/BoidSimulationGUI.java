package Boids;

import javax.swing.*;
import java.awt.*;

public class BoidSimulationGUI extends JFrame {
    private final BoidPanel boidPanel;

    public BoidSimulationGUI() {
        super("Boid Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for boids
        boidPanel = new BoidPanel();
        add(boidPanel, BorderLayout.CENTER);

        // Create sliders for factors
        JSlider alignmentSlider = createSlider("Alignment Factor", 0, 100, (int) (Boid.ALIGNMENT_FACTOR * 100));
        JSlider cohesionSlider = createSlider("Cohesion Factor", 0, 100, (int) (Boid.COHESION_FACTOR * 100));
        JSlider separationSlider = createSlider("Separation Factor", 0, 100, (int) (Boid.SEPARATION_FACTOR * 100));

        // Add action listeners to sliders
        alignmentSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                Boid.ALIGNMENT_FACTOR = source.getValue() / 100.0;
            }
        });

        cohesionSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                Boid.COHESION_FACTOR = source.getValue() / 100.0;
            }
        });

        separationSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                Boid.SEPARATION_FACTOR = source.getValue() / 100.0;
            }
        });

        // Create a panel for sliders
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.add(createSliderPanel("Alignment Factor", alignmentSlider));
        sliderPanel.add(createSliderPanel("Cohesion Factor", cohesionSlider));
        sliderPanel.add(createSliderPanel("Separation Factor", separationSlider));

        // Add the slider panel to the frame
        add(sliderPanel, BorderLayout.EAST);

        // Set up the timer for animation
        Timer timer = new Timer(20, e -> boidPanel.repaint());
        timer.start();

        // Set up the frame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSliderPanel(String label, JSlider slider) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel sliderLabel = new JLabel(label);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(sliderLabel);
        panel.add(slider);

        return panel;
    }

    private JSlider createSlider(String label, int min, int max, int initialValue) {
        JSlider slider = new JSlider(min, max, initialValue);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        return slider;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BoidSimulationGUI::new);
    }
}

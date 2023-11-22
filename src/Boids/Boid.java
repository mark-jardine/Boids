package Boids;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boid {
    //src: https://www.red3d.com/cwr/boids/ by Craig Reynolds
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    public static final int SIZE = 10; //boid width, height dimension
    private double xVel, yVel; //velocities (in x-axis and y-axis)
    private double xPos, yPos; //x and y co-ordinates
    private static final Random r = new Random();
    private final Color boidColour = Color.getHSBColor(r.nextFloat(), 0.6f, 0.75f);
    private static final int VIEW_DISTANCE = 40; //radius of a circle around each boid that defines the area that they can perceive other boids within
    public static double SEPARATION_FACTOR = 1.;
    public static double ALIGNMENT_FACTOR = 0.05;
    public static double COHESION_FACTOR = 0.005;
    private static final double SEPARATION_DISTANCE = 10.0;
    public static double MAX_SPEED = 100.0;
    private static final int BOID_FOV = 140;

    public Boid() {
        xPos = r.nextInt(BoidPanel.PANEL_WIDTH - SIZE);
        yPos = r.nextInt(BoidPanel.PANEL_HEIGHT - SIZE);
        xVel = -4 + (4 - (-4)) * r.nextDouble();
        yVel = -4 + (4 - (-4)) * r.nextDouble();
        normalizeVelocity();
        activeBoids.add(this);
    }

    public Color getColour() {
        return this.boidColour;
    }

    public void setxVel(double value) {
        xVel = value;
    }

    public void setyVel(double value) {
        yVel = value;
    }
    public double getxVel() {return this.xVel;}
    public double getyVel() {return this.yVel;}
    public double getxPos() {
        return xPos;
    }
    public double getyPos() {
        return yPos;
    }

    public void move() {
        // Update position based on velocity
        xPos = (xPos + xVel + BoidPanel.PANEL_WIDTH) % BoidPanel.PANEL_WIDTH;
        yPos = (yPos + yVel + BoidPanel.PANEL_HEIGHT) % BoidPanel.PANEL_HEIGHT;

        // Avoid overlapping
        avoidOverlapping();

        align();
        cohere();
        separate();
    }



    private void avoidOverlapping() {
        for (Boid other : getLocalBoids()) {
            double distance = Math.sqrt(Math.pow(this.xPos - other.xPos, 2) + Math.pow(this.yPos - other.yPos, 2));

            if (distance < SIZE) {
                // Calculate a vector pointing away from the other boid
                Vector2D avoidanceVector = new Vector2D(this.xPos - other.xPos, this.yPos - other.yPos).normalize();

                // Adjust velocities to avoid overlapping
                this.xVel += avoidanceVector.getX();
                this.yVel += avoidanceVector.getY();
                other.xVel -= avoidanceVector.getX();
                other.yVel -= avoidanceVector.getY();
            }
        }
    }


    private void normalizeVelocity() {
        double length = Math.sqrt(xVel * xVel + yVel * yVel);
        if (length > 0) {
            xVel = xVel / length;
            yVel = yVel / length;
        }
    }

    public static ArrayList<Boid> spawn(int quantity) {
        ArrayList<Boid> boids = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            boids.add(new Boid());
        }

        return boids;
    }

    private ArrayList<Boid> getLocalBoids() {
        ArrayList<Boid> localBoids = new ArrayList<>();

        for (Boid b : activeBoids) {
            if (b != this) {
                double distance = Math.sqrt(Math.pow(b.xPos - this.xPos, 2) + Math.pow(b.yPos - this.yPos, 2));

                // Calculate angle between the current boid and the target boid
                double angle = Math.atan2(b.yPos - this.yPos, b.xPos - this.xPos);

                // Calculate the angle difference between the direction of the current boid and the angle to the target boid
                double angleDifference = Math.abs(angle - Math.atan2(yVel, xVel));

                // Consider the boid only if it is within the field of view (e.g., 60 degrees)
                if (distance <= VIEW_DISTANCE && angleDifference <= Math.toRadians(BOID_FOV)){
                    localBoids.add(b);
                }
            }
        }

        return localBoids;
    }


    private void separate() {
        Vector2D separation = new Vector2D(0, 0);

        for (Boid other : getLocalBoids()) {
            double distance = Math.sqrt(Math.pow(this.xPos - other.xPos, 2) + Math.pow(this.yPos - other.yPos, 2));
            if (distance > 0 && distance < SEPARATION_DISTANCE) {
                Vector2D diff = new Vector2D(this.xPos - other.xPos, this.yPos - other.yPos);
                separation = separation.add(diff.normalize().scale(1 / distance));
            }
        }

       // System.err.println("Separation- x = " + separation.getX() + ", y = " + separation.getY());

        this.xVel += separation.getX() * SEPARATION_FACTOR;
        this.yVel += separation.getY() * SEPARATION_FACTOR;
    }


    private void align() {
        ArrayList<Boid> localBoids = getLocalBoids();
        int numLocalBoids = localBoids.size();

        if (numLocalBoids == 0) {
            // If no local boids, maintain current velocity
            return;
        }

        // Calculate average velocity of local boids
        double avg_xVel = 0;
        double avg_yVel = 0;

        for (Boid b : localBoids) {
            avg_xVel += b.xVel;
            avg_yVel += b.yVel;
        }

        avg_xVel /= numLocalBoids;
        avg_yVel /= numLocalBoids;

        Vector2D avgVelocity = new Vector2D(avg_xVel, avg_yVel).normalize().scale(MAX_SPEED * ALIGNMENT_FACTOR * 2);

        // Adjust the current velocity towards the average velocity without changing magnitude too much
        double accelerationFactor = 0.1;
        setxVel(getxVel() + accelerationFactor * (avgVelocity.getX() - getxVel()));
        setyVel(getyVel() + accelerationFactor * (avgVelocity.getY() - getyVel()));
    }

    private void cohere() {
        ArrayList<Boid> localBoids = getLocalBoids();
        int numLocalBoids = localBoids.size();

        if (numLocalBoids == 0) {
            // If no local boids, maintain current position
            return;
        }

        // Calculate average position of local boids
        double avg_xPos = 0;
        double avg_yPos = 0;

        for (Boid b : localBoids) {
            avg_xPos += b.xPos;
            avg_yPos += b.yPos;
        }

        avg_xPos /= numLocalBoids;
        avg_yPos /= numLocalBoids;

        // Move towards the average position
        double desiredX = avg_xPos - xPos;
        double desiredY = avg_yPos - yPos;
        Vector2D desiredVelocity = new Vector2D(desiredX, desiredY).normalize().scale(MAX_SPEED * COHESION_FACTOR * 2);

        // Adjust the current velocity towards the desired velocity without changing magnitude too much
        double accelerationFactor = 0.1;
        setxVel(getxVel() + accelerationFactor * (desiredVelocity.getX() - getxVel()));
        setyVel(getyVel() + accelerationFactor * (desiredVelocity.getY() - getyVel()));
    }

}


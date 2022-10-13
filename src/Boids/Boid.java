package Boids;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Boid {
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    public static final int size = 30; //boid width, height dimension
    private double xVel, yVel; //velocities (in x-axis and y-axis)
    private int xPos, yPos; //x and y co-ordinates
    private static final Random r = new Random();
    private final Color boidColour = Color.getHSBColor(r.nextFloat(), 0.6f, 0.75f);//random HSB value
    private static final int viewDistance = 60; //radius of a circle around each boid that defines the area that they can perceive other boids within
    private static final float alignmentBias = 0.35f; //Strength of alignment between boids

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH - size);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT - size);
        xVel = r.nextInt(8) + 1; //random x-velocity
        yVel = r.nextInt(8) + 1; //random y-velocity
        activeBoids.add(this);
    }

    public Boid(int _xPos, int _yPos) { //just declare co-ords
        xPos = _xPos;
        yPos = _yPos;
        xVel = r.nextInt(5);
        yVel = r.nextInt(5);
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

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void move() {

        //invert x velocity if horizontal boundary reached
        if (getxPos() >= AppPanel.PANEL_WIDTH - size || getxPos() < 0) {
            setxVel(xVel * -1);
        }
        xPos += xVel;

        //invert y velocity if vertical boundary reached
        if (getyPos() >= AppPanel.PANEL_HEIGHT - size || getyPos() < 0) {
            setyVel(yVel * -1);
        }
        yPos += yVel;

        //Allignment
        align();

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
            if (b != this && (Math.abs(b.xPos - this.xPos) <= viewDistance || Math.abs(b.yPos - this.yPos) <= viewDistance)) {
                localBoids.add(b);
            }
        }

        return localBoids;
    }

    //src: https://www.red3d.com/cwr/boids/ by Craig Reynolds
    private void seperate() { //steer to avoid crowding local boids

    }

    private void align() { //steer towards average heading of local boids


        int numLocalBoids =getLocalBoids().size() ;
        System.out.println("Number of local boids: "+numLocalBoids);
        if(numLocalBoids == 0) return; //if no local boids

        //Get average heading of local boids
        //(xvel + yvel) * bias
        double total_x = 0;
        double total_y = 0;

        for (Boid b : getLocalBoids()) {
            total_x+=b.xVel;
            total_y+=b.yVel;
        }

        Vector2D avgHeading = new Vector2D(
                total_x/numLocalBoids,
                total_y/numLocalBoids);

        //Set this boid's heading to avg heading
        this.setxVel(avgHeading.getX() * alignmentBias);
        this.setyVel(avgHeading.getY()* alignmentBias);

    }

    private void cohere() { //steer to move toward average position of local boids

    }


}


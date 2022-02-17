package Boids;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boid {
    final static int MAX_INSTANCES = 200; //max number of boids that can be active
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    public static final int size = 30; //boid width, height dimension
    private int xVel, yVel; //velocities (in x-axis and y-axis)
    private int xPos, yPos; //x and y co-ordinates
    private static final Random r = new Random();
    private final Color boidColour = Color.getHSBColor(r.nextFloat(), 0.6f, 0.75f);//random HSB value
    private static int viewDistance = 60; //radius of a circle around each boid that defines the area that they can perceive other boids within

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH - size);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT - size);
        xVel = r.nextInt(8)+1; //random x-velocity
        yVel = r.nextInt(8)+1; //random y-velocity
        activeBoids.add(this);
    }

    public Boid(int _xPos, int _yPos) { //just declare co-ords
        xPos = _xPos;
        yPos = _yPos;
        xVel = r.nextInt(5);
        yVel = r.nextInt(5);
        activeBoids.add(this);
    }

    public Boid(int _xPos, int _yPos, int _xVel, int _yVel) { //declare co-ords and velocity
        xPos = _xPos;
        yPos = _yPos;
        this.xVel = _xVel;
        this.yVel = _yVel;
        activeBoids.add(this);
    }

    public Color getColour() {
        return this.boidColour;
    }

    public void setxVel(int value) {
        xVel = value;
    }

    public void setyVel(int value) {
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
            xPos += xVel;
        } else xPos += xVel;

        //invert y velocity if vertical boundary reached
        if (getyPos() >= AppPanel.PANEL_HEIGHT - size || getyPos() < 0) {
            setyVel(yVel * -1);
            yPos += yVel;
        } else yPos += yVel;

    }

    public static ArrayList<Boid> spawn(int quantity) {
        int x;
        int y;
        ArrayList<Boid> boids = new ArrayList<>();


        // if ((quantity + activeBoids.size()) < MAX_INSTANCES) {
        for (int i = 0; i < quantity; i++) {
            boids.add(new Boid());
        }
        //} else return null;


        return boids;
    }

    private ArrayList<Boid> getLocalBoids(int localDistance){
        ArrayList<Boid> localBoids = new ArrayList<>();

        return localBoids;
    }

    //src: https://www.red3d.com/cwr/boids/ by Craig Reynolds
    private void seperate() { //steer to avoid crowding local boids

        for (Boid b : activeBoids) {

        }

    }

    private void align() { //steer towards average heading of local boids


    }

    private void cohere() { //steer to move toward average position of local boids

    }


}


package Boids;

import java.util.ArrayList;
import java.util.Random;

public class Boid {
    final static int MAX_INSTANCES = 200; //max number of boids that can be active
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    private int xVel, yVel; //velocities (in x-axis and y-axis)
    private int xPos, yPos; //x and y co-ordinates
    private static final Random r = new Random();

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT);
        xVel = 5;
        yVel = 5;
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
        if (getxPos() >= AppPanel.PANEL_WIDTH || getxPos() < 0 ) {
            setxVel(xVel * -1);
            xPos += xVel;
        } else xPos += xVel;

        //invert y velocity if vertical boundary reached
        if (getyPos() >= AppPanel.PANEL_HEIGHT || getyPos() < 0) {
            setyVel(yVel * -1);
            yPos+=yVel;
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
}


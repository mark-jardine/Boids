package Boids;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

//contain instances of individual boids
public class Boid {
    final static int MAX_INSTANCES = 200; //max number of boids that can be active
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    private int xVel, yVel; //velocities (in x-axis and y-axis)
    private int xPos, yPos; //x and y co-ordinates
    private static Random r = new Random();

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT);
        xVel = r.nextInt(3);
        yVel = r.nextInt(3);
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

    public int getxVel() {
        return xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void move() {

        if (this.getxPos() < AppPanel.PANEL_WIDTH && this.getxPos() > 0)
            this.xPos += this.xVel;
        else {
            this.setxVel(this.xVel * -1);
        } //invert if horizontal boundary reached

        if (this.getyPos() < AppPanel.PANEL_HEIGHT && this.getyPos() > 0)
            this.yPos += this.yVel;
        else this.setyVel(this.yVel * -1); //invert if vertical boundary reached
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


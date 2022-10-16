package Boids;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boid {
    //src: https://www.red3d.com/cwr/boids/ by Craig Reynolds
    public static ArrayList<Boid> activeBoids = new ArrayList<>();
    public static final int size = 30; //boid width, height dimension
    private double xVel, yVel; //velocities (in x-axis and y-axis)
    private double xPos, yPos; //x and y co-ordinates
    private static final Random r = new Random();
    private final Color boidColour = Color.BLACK;//random HSB value: Color.getHSBColor(r.nextFloat(), 0.6f, 0.75f)
    private static final int viewDistance = 40; //radius of a circle around each boid that defines the area that they can perceive other boids within
    private static final double minSpeed = 2;

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH - size);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT - size);
        xVel = -4 + (4 - (-4)) * r.nextDouble(); //random x-velocity
        yVel = -4 + (4 - (-4)) * r.nextDouble(); //random y-velocity
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

    public void setxPos(double value){xPos = value;}

    public void setyPos(double value){yPos = value;}

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void move() {

        //invert x position if horizontal boundary reached
        if(getxPos() >= AppPanel.PANEL_WIDTH-size) setxPos(0);
        else if (getxPos() < 0) setxPos(AppPanel.PANEL_WIDTH- size);
        setxPos(getxPos() + xVel + minSpeed) ;

        //invert u position if horizontal boundary reache
        if(getyPos() >= AppPanel.PANEL_HEIGHT-size) setyPos(0);
        else if (getyPos() < 0) setyPos(AppPanel.PANEL_HEIGHT- size);
        setyPos(getyPos() + yVel + minSpeed);

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

    private void seperate() { //steer to avoid crowding local boids

    }

    private void align() { //steer towards average heading of local boids

        int numLocalBoids =getLocalBoids().size() ;
        System.out.println("Number of local boids: "+numLocalBoids);
        if(numLocalBoids == 0) return; //if no local boids

        //Get average heading of local boids
        //(xvel + yvel)
        double total_x = 0;
        double total_y = 0;

        for (Boid b : getLocalBoids()) {
            total_x+=b.xVel;
            total_y+=b.yVel;
        }

        Vector2D desiredVelocity = new Vector2D(
                total_x/numLocalBoids,
                total_y/numLocalBoids);

        //Set this boid's heading to avg heading
        this.setxVel(this.xVel + (desiredVelocity.getX()- this.xVel));
        this.setyVel(this.yVel + (desiredVelocity.getY()- this.yVel));

    }

    private void cohere() { //steer to move toward average position of local boids

    }


}


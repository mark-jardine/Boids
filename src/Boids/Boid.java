package Boids;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

//contain instances of individual boids
public class Boid {
    final static int MAX_INSTANCES = 200; //max number of boids that can be active
    public static ArrayList<Boid> activeBoids;
    private int xVel, yVel; //velocities (in x-axis and y-axis)
    private int xPos, yPos; //x and y co-ordinates
    private static Random r = new Random();

    public Boid() {
        xPos = r.nextInt(AppPanel.PANEL_WIDTH);
        yPos = r.nextInt(AppPanel.PANEL_HEIGHT);
        xVel = r.nextInt(30);
        yVel = r.nextInt(30);
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

    public void move(){
        this.xPos += this.xVel;
        this.yPos += this.yVel;
    }


  /*  public static Triangle_Shape[] spawn(int quantity) { //return array of Triangle_Shape obj -> co-ords of each point in the triangle for each Boid being spawned
        Random r = new Random();
        int x;
        int y;
        Triangle_Shape[] triangles = new Triangle_Shape[quantity];

        if ((quantity + activeBoids.size()) < MAX_INSTANCES) {
            for (int i = 0; i < quantity; i++) {
                //get random co-ords
                x = r.nextInt(AppPanel.PANEL_WIDTH);
                y = r.nextInt(AppPanel.PANEL_HEIGHT);

                //calculate other points in triangle
                int y1 = y + 16;
                int x2 = x + 16;
                int y2 = y + 8;

                triangles[i] = new Triangle_Shape(new Point2D.Double(x, y), new Point2D.Double(x, y1), new Point2D.Double(x2, y2)); //add boid shape to triangle array



            }

        } else return null;


        return triangles;
    }*/

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


////shape of boid src-> https://www.delftstack.com/howto/java/java-draw-triangle/
//class Triangle_Shape extends Path2D.Double {
//    public Triangle_Shape(Point2D... points) {
//        moveTo(points[0].getX(), points[0].getY());
//        lineTo(points[1].getX(), points[1].getY());
//        lineTo(points[2].getX(), points[2].getY());
//        closePath();
//    }
//}

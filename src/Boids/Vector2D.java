package Boids;


public class Vector2D {

    private double x,y;

    public Vector2D(){

    }

    public Vector2D(double _x, double _y){
        this.x=_x;
        this.y=_y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}

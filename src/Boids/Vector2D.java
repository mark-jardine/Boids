package Boids;

public class Vector2D {
    private final double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D scale(double factor) {
        return new Vector2D(this.x * factor, this.y * factor);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        if (mag != 0) {
            return new Vector2D(x / mag, y / mag);
        } else {
            return new Vector2D(0, 0);
        }
    }
}
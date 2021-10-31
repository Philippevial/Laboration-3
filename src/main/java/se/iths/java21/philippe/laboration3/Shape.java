package se.iths.java21.philippe.laboration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private Color color;
    private final double x;
    private final double y;
    private double size;

    public Shape(Color color, double x, double y, double size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Shape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public abstract void draw(GraphicsContext graphicsContext);

    public abstract boolean isInside(double x, double y);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

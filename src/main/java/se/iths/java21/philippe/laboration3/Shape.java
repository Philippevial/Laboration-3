package se.iths.java21.philippe.laboration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {
    private final double x;
    private final double y;
    private final double size;
    private Color color;

    public Shape(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public Shape(Shape shape) {
        this.x = shape.x;
        this.y = shape.y;
        this.size = shape.size;
        this.color = shape.color;
    }

    public abstract Shape copyOf();

    public abstract void draw(GraphicsContext graphicsContext);

    public abstract String drawSVG();

    public abstract void setSize(double size);

    public abstract boolean isInside(double x, double y);

    public double getSize() {
        return size;
    }

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

}

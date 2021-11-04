package se.iths.java21.philippe.laboration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {
    private Color color;
    private final double x;
    private final double y;
    private final double size;


    public Shape(Color color, double x, double y, double size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Shape(Shape shape) {
        this.color = shape.color;
        this.x = shape.x;
        this.y = shape.y;
        this.size = shape.size;
    }

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

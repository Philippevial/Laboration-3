package se.iths.java21.philippe.laboration3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.Shape;

public final class Rectangle extends Shape {

    private final double size;

    public Rectangle(Color color, double x, double y, double size) {
        super(color, x, y);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        double halfSize = size * 0.5;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(getX() - halfSize, getY() - halfSize, size, size);
    }

    @Override
    public boolean isInside(double x, double y) {
        return false;
    }
}
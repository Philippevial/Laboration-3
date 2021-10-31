package se.iths.java21.philippe.laboration3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.Shape;

public final class Square extends Shape {

    private final double size;

    public Square(Color color, double x, double y, double size) {
        super(color, x, y, size);
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
        double dx = x - getX();
        double dy = y - getY();

        double distanceFromCircleCenterSquared = dx * dx + dy * dy;
        return distanceFromCircleCenterSquared < size*size;
    }
}
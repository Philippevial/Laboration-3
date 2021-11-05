package se.iths.java21.philippe.laboration3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.Shape;

public final class Square extends Shape {

    private double size;

    public Square(double x, double y, double size, Color color) {
        super(x, y, size, color);
        this.size = size;
    }

    public Square(Square shape) {
        super(shape);
        this.size = shape.size;
    }

    @Override
    public Shape copyOf() {
        return new Square(this);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        double halfSize = size * 0.5;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(getX() - halfSize, getY() - halfSize, size, size);
    }

    @Override
    public String drawSVG() {
        String convertColor = "#" + getColor().toString().substring(2, 10);

        return "<rect x=\"" + (getX() - getSize()) + "\" " +
                "y=\"" + (getY() - getSize()) + "\" " +
                "width=\"" + (2 * getSize()) + "\" " +
                "height=\"" + (2* getSize()) + "\" " +
                "fill=\"" + convertColor + "\" />";
    }

    @Override
    public boolean isInside(double mouseX, double mouseY) {
        double leftX = getX() - size;
        double topY = getY() - size;

        return mouseX >= leftX &&
                mouseX <= leftX + 2 * size &&
                mouseY >= topY &&
                mouseY <= topY + 2 * size;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Square " + super.getColor();
    }
}
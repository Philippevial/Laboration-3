package se.iths.java21.philippe.laboration3;

import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.shapes.Circle;
import se.iths.java21.philippe.laboration3.shapes.Square;


public class ShapeBuilder {

    public static Shape circleOf(double x, double y, double radius, Color color) {
        return new Circle(x, y, radius, color);
    }

    public static Shape squareOf(double x, double y, double size, Color color) {
        return new Square(x,y,size, color);
    }

}

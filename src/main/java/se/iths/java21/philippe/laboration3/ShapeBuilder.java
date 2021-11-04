package se.iths.java21.philippe.laboration3;

import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.shapes.Circle;
import se.iths.java21.philippe.laboration3.shapes.Square;


public class ShapeBuilder {

    public static Shape circleOf(double x, double y, double radius, Color color) {
        return new Circle(color, x, y, radius);
    }

    public static Shape squareOf(Color color, double x, double y, double size) {
        return new Square(color,x,y,size);
    }

    public static Shape circleOf(Shape shape) {
        return new Circle(shape);
    }

    public static Shape squareOf(Shape shape) {
        return new Square(shape);
    }

}

package se.iths.java21.philippe.laboration3;

import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.shapes.Circle;
import se.iths.java21.philippe.laboration3.shapes.Rectangle;


public class Shapes {

    //Factory metod, tillverka objekt av den här typen.
    public static Shape circleOf(double x, double y, double radius, Color color) {
        return new Circle(color, x, y, radius);
    }

    public static Shape rectangleOf(Color color, double x, double y,double size) {
        return new Rectangle(color,x,y,size);
    }
}

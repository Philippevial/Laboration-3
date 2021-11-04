package se.iths.java21.philippe.laboration3;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.java21.philippe.laboration3.shapes.Circle;
import se.iths.java21.philippe.laboration3.shapes.Square;

import java.util.*;


public class Model {

    private final ObjectProperty<Color> color;
    public final ObjectProperty<Integer> size;

    ObservableList<Shape> shapes;

    Deque<ObservableList<Shape>> redo;
    Deque<ObservableList<Shape>> undo;


    public Model() {
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleObjectProperty<>(1);
        this.shapes = FXCollections.observableArrayList();
        this.undo = new ArrayDeque<>();
        this.redo = new ArrayDeque<>();
    }

    public Color getColor() {
        return color.get();
    }

    public Integer getSize() {
        return size.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }


    public ObservableList<Shape> getTempList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();

        for (Shape shape : shapes) {
            if(shape.getClass() == Circle.class)
                tempList.add(ShapeBuilder.circleOf(shape));
            if(shape.getClass()== Square.class)
                tempList.add(ShapeBuilder.squareOf(shape));
        }
        return tempList;
    }
}

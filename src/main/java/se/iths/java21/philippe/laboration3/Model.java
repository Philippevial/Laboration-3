package se.iths.java21.philippe.laboration3;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;


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

        for (Shape shape : shapes)
            tempList.add(shape.copyOf());

        return tempList;
    }

    public void undo() {
        if (undo.isEmpty()) {
            return;
        }
        ObservableList<Shape> temp = getTempList();
        redo.addLast(temp);
        shapes = undo.removeLast();
    }

    public void redo() {
        if (redo.isEmpty())
            return;
        ObservableList<Shape> temp = getTempList();
        undo.addLast(FXCollections.observableArrayList(temp));
        shapes = redo.removeLast();
    }
}

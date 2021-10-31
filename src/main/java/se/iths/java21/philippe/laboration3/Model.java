package se.iths.java21.philippe.laboration3;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class Model {

    private final StringProperty text;
    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;
    public final ObjectProperty<Integer> size;


    ObservableList<String> observableList =
            FXCollections.observableArrayList();

    List<Shape> shapes = new ArrayList<>();

    public Model() {
        this.text = new SimpleStringProperty();
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleObjectProperty<>(1);

    }

    public Color getColor() {
        return color.get();
    }

    public Integer getSize() {
        return size.get();
    }

    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }

    public void setSize(Integer size) {
        this.size.set(size);
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }




}

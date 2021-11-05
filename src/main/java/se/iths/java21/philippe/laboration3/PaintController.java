package se.iths.java21.philippe.laboration3;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaintController {

    Model model;
    @FXML
    public Button redoButton;
    @FXML
    public Button undoButton;
    @FXML
    public Canvas canvas;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public RadioButton squareButton;
    @FXML
    public RadioButton circleRadioButton;
    @FXML
    public RadioButton selectRadioButton;
    @FXML
    public Spinner<Integer> sizeSpinner;

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public PaintController() {
    }
    public void initialize() {
        model = new Model();
        ToggleGroup group = new ToggleGroup();
        circleRadioButton.setToggleGroup(group);
        squareButton.setToggleGroup(group);
        selectRadioButton.setToggleGroup(group);

        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        model.size.bindBidirectional(sizeSpinner.getValueFactory().valueProperty());
    }

    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

    public void onSave() {
        SvgConverter svgConverter = new SvgConverter();
        svgConverter.saveSVGFile(model);
    }

    public void onExit() {
        Platform.exit();
    }

    public void canvasClicked(MouseEvent event) {
        if (selectRadioButton.isSelected()) {
            ObservableList<Shape> temp = model.getTempList();
            model.undo.addLast(temp);
            changeShapeColor(event);
            changeShapeSize(event);
        } else {
            ObservableList<Shape> temp = model.getTempList();
            if (squareButton.isSelected()) {
                model.undo.addLast(temp);
                model.shapes.add(ShapeBuilder.squareOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            } else if (circleRadioButton.isSelected()) {
                model.undo.addLast(temp);
                model.shapes.add(ShapeBuilder.circleOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
            }
        }
        draw();
    }

    private void changeShapeSize(MouseEvent event) {
        model.shapes.stream()
                .filter(shape -> shape.isInside(event.getX(), event.getY()))
                .reduce((first, second) -> second)
                .ifPresent(shape -> shape.setSize(sizeSpinner.getValue()));
    }

    private void changeShapeColor(MouseEvent event) {
        model.shapes.stream()
                .filter(shape -> shape.isInside(event.getX(), event.getY()))
                .reduce((first, second) -> second)
                .ifPresent(shape -> shape.setColor(model.getColor()));
    }

    public void redo() {
        model.redo();
        draw();
    }

    public void undo() {
        model.undo();
        draw();
    }
}

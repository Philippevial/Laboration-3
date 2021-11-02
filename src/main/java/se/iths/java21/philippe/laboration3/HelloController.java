package se.iths.java21.philippe.laboration3;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


import javax.imageio.ImageIO;
import java.io.File;


public class HelloController {

    Model model;
    @FXML
    public Button redoButton;
    @FXML
    private Button undoButton;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    public RadioButton squareButton;
    @FXML
    public RadioButton circleRadioButton;
    @FXML
    public RadioButton selectRadioButton;
    @FXML
    private Spinner<Integer> sizeSpinner;


    public HelloController() {
    }

    public HelloController(Model model) {
        this.model = model;
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
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("Artwork.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image" + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

    public void canvasClicked(MouseEvent event) {
        if (selectRadioButton.isSelected()) {
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(shape -> shape.setColor(model.getColor()));
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(shape -> shape.setSize(sizeSpinner.getValue()));
        }
        if (squareButton.isSelected()) {
            model.shapes.add(Shapes.squareOf(model.getColor(), event.getX(), event.getY(), model.getSize()));
        } else if (circleRadioButton.isSelected()) {
            model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
        }
        draw();
    }

    private void redo() {
        if(model.shapesBackup.isEmpty()) {
            return;
        }
        model.shapes.add(model.shapesBackup.getLast());
        draw();
    }
    private  void undo() {
        var lastRemoved = model.shapes.getLast();
        model.shapesBackup.add(lastRemoved);

        if(model.shapes.isEmpty())
            return;
        model.shapes.removeLast();
        draw();
    }

    public void undoButtonPressed(MouseEvent event) {
        if(undoButton.isPressed())
            undo();
    }

    public void redoButtonPressed(MouseEvent event) {
        if(redoButton.isPressed())
            redo();
    }
}
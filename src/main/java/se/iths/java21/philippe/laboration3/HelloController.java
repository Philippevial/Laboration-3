package se.iths.java21.philippe.laboration3;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import javax.imageio.ImageIO;
import java.io.File;


public class HelloController {

    Model model;

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    public RadioButton rectangleRadioButton;
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
        if (rectangleRadioButton.isSelected())
            model.shapes.add(Shapes.rectangleOf(model.getColor(), event.getX(), event.getY(), model.getSize()));
        else if (circleRadioButton.isSelected())
            model.shapes.add(Shapes.circleOf(event.getX(), event.getY(), model.getSize(), model.getColor()));
        else if (event.isAltDown()){
            model.shapes.stream()
                    .filter(shape -> shape.isInside(event.getX(), event.getY()))
                    .findFirst().ifPresent(shape -> shape.setColor(Color.RED));
        }
        draw();
    }
}
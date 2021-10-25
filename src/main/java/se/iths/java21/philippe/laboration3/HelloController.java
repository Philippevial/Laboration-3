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
    private Button rectangleButton;
    @FXML
    private Button circleButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button pointButton;

    @FXML
    private Spinner<Integer> shapeSize;

    @FXML
    private CheckBox eraser;

    public HelloController() {
    }

    public HelloController(Model model) {
        this.model = model;
    }

    public void initialize() {
        model = new Model();
        model.setColor(Color.BLACK);
        canvas.widthProperty().addListener(observable -> draw());
        canvas.heightProperty().addListener(observable -> draw());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        model.size.bindBidirectional(shapeSize.getValueFactory().valueProperty());
    }

    private void draw() {
        var gc = canvas.getGraphicsContext2D();
        //double size = Double.parseDouble(shapeSize.getText());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            gc.setFill(shape.getColor());
            gc.fillOval(shape.getX(), shape.getY(), model.getSize(), model.getSize());
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
//        var context = canvas.getGraphicsContext2D();
//        canvas.setOnMousePressed(e -> paintAction(context, e));
        model.shapes.add(new Shape(model.getColor(), event.getX(), event.getY()));
        draw();

    }



    //För att ta bort? eller använda till att justera shapes?
//    public void clickDelete(ActionEvent event) {
//        personView.getItems().remove(personView.getSelectionModel().getSelectedItem());
//    }

}
package se.iths.java21.philippe.laboration3;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;


public class HelloController {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField shapeSize;

    @FXML
    private CheckBox eraser;

    public void initialize() {

    }

    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("Artwork.png"));
        } catch(Exception e) {
            System.out.println("Failed to save image" + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

    public void canvasClicked() {
        var context = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(e -> paintAction(context, e));
    }

    private void paintAction(GraphicsContext context, MouseEvent e) {
        double size = Double.parseDouble(shapeSize.getText());
        double x = e.getX() - size / 2;
        double y = e.getY() - size / 2;

        if (eraser.isSelected()) {
            context.clearRect(x, y, size, size);
        } else {
            context.setFill(colorPicker.getValue());
            context.fillOval(x, y, size, size);
        }
    }

    public void draggedOnCanvas() {
        var context = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> paintAction(context, e));
    }

    //För att ta bort? eller använda till att justera shapes?
//    public void clickDelete(ActionEvent event) {
//        personView.getItems().remove(personView.getSelectionModel().getSelectedItem());
//    }

}
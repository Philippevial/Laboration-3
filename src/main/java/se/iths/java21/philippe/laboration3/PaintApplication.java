package se.iths.java21.philippe.laboration3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaintApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PaintApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Paint App");
        stage.setScene(scene);
        stage.show();
        PaintController helloController = fxmlLoader.getController();
        helloController.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
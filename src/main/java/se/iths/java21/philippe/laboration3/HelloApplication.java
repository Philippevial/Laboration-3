package se.iths.java21.philippe.laboration3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("hello-view.fxml"))));
        stage.setTitle("Paint App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
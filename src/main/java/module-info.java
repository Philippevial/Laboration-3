module se.iths.java21.philippe.laboration3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens se.iths.java21.philippe.laboration3 to javafx.fxml;
    exports se.iths.java21.philippe.laboration3;
}
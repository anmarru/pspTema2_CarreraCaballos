module tema2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens tema2 to javafx.fxml;
    exports tema2;
}

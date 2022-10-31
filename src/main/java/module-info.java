module com.example.knightmove {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.knightmove to javafx.fxml;
    exports com.example.knightmove;
}
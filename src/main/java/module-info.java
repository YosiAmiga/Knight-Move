module com.example.knightmove {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires json.simple;
<<<<<<< HEAD
    requires javafx.base;
=======
    requires junit;
    requires org.junit.jupiter.api;
    requires testfx.core;
    requires testfx.junit;
    requires testFx;
    requires hamcrest.core;
>>>>>>> master

    opens com.example.knightmove to javafx.fxml;
    exports com.example.knightmove;
    exports com.example.knightmove.controllers;
    opens com.example.knightmove.controllers to javafx.fxml;
    opens com.example.knightmove.Model to javafx.base;
    exports com.example.knightmove.Tests;
    opens com.example.knightmove.Tests to javafx.fxml;
}
package com.search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent  root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/search/welcome.fxml")));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/icon.jpg"))));
        stage.setScene(scene);
        stage.setTitle("Zzuli Search");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
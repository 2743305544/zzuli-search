package com.search;

import com.search.constant.PathConstants;
import com.search.constant.SystemConstants;
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
        Parent  root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PathConstants.WELCOME_PAGE_PATH)));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(PathConstants.ICON_PATH))));
        stage.setScene(scene);
        stage.setTitle(SystemConstants.TITLE);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
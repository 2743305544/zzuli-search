package com.search.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Jumputils {
    public static void Jump(String fxmlanme, ActionEvent event)  {
        Parent subPage = null;
        try {
            subPage = FXMLLoader.load(Objects.requireNonNull(Jumputils.class.getResource("/com/search/"+fxmlanme)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene subPageScene = new Scene(subPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(subPageScene);
        window.show();
    }
}

package com.search.utils;

import com.search.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 34011
 */
public class Jumputils {
    public static void Jump(String fxmlanme, ActionEvent event)  {
        Parent subPage = null;
        try {
//            System.out.println(fxmlanme);
//            System.out.println(HelloApplication.class);
//            System.out.println("1111111111111111111111111111111111111111111"+HelloApplication.class.getResource("/com/search/"+fxmlanme));
            subPage = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("/com/search/"+fxmlanme)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene subPageScene = new Scene(subPage);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(subPageScene);
        window.show();
    }
}

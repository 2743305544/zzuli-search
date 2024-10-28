package com.search;

import animatefx.animation.GlowBackground;
import animatefx.animation.JackInTheBox;
import animatefx.animation.RollOut;
import com.search.dynamic.FadeTransitionTools;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 34011
 */
public class AboutMeController implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Label IndexLabel;
    @FXML
    private Label indexLabel1;
    @FXML
    private Label indexLabel2;
    @FXML
    private Label indexLabel3;
    @FXML
    private ImageView Myavatar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransitionTools.fadeout(MainPane);
        FadeTransitionTools.fadeInComponent(IndexLabel,5);
        FadeTransitionTools.fadeInComponent(indexLabel1,10);
        FadeTransitionTools.fadeInComponent(indexLabel2,15);
        FadeTransitionTools.fadeInComponent(indexLabel3,20);
        FadeTransitionTools.fadeInComponent(Myavatar,5);
    }
    @FXML
    public void clickAvatar(MouseEvent mouseEvent) {
        new JackInTheBox(Myavatar).play();
    }

    //计划整活
//    public void KeyEnter(KeyEvent keyEvent) {
//
//    }
}

package com.search;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceInDown;
import animatefx.animation.Swing;
import animatefx.animation.Wobble;
import com.search.dynamic.DynamicWave;
import com.search.dynamic.FadeTransitionTools;
import com.search.utils.Jumputils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private MFXButton button1,button2,button3,button4,button5,button6,button7,button8;

    @FXML
    private AnchorPane wavePane;

    @FXML
    private AnchorPane indexPane;

    private Boolean isTriggered = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransitionTools.fadeout(indexPane);
        new Thread(() -> {
            Canvas canvas = new Canvas(1040, 300);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    DynamicWave.draw(gc, canvas.getWidth(), canvas.getHeight());
                }
            };
            timer.start();
            wavePane.getChildren().add(canvas);
            indexPane.getChildren().add(wavePane);
        }).start();
    }
    /**
     * 按钮动态效果
     */
    @FXML
    public void buttonEntered(MouseEvent event) {
        if(!isTriggered){
            new Wobble((Node) event.getSource()).play();
            isTriggered = true;
        }
    }
    @FXML
    public void buttonExited(MouseEvent event) {
        if(isTriggered){
            isTriggered = false;
        }
    }
    @FXML
    public void Button1Clicked(ActionEvent event) {
        FadeTransitionTools.fade(indexPane);
        Jumputils.Jump("Msg.fxml",event);
    }
    @FXML
    public void Button2Clicked(ActionEvent event) {

    }
    @FXML
    public void Button3Clicked(ActionEvent event) {

    }
    @FXML
    public void Button4Clicked(ActionEvent event) {

    }
    @FXML
    public void Button5Clicked(ActionEvent event) {

    }
    @FXML
    public void Button6Clicked(ActionEvent event) {

    }
    @FXML
    public void Button7Clicked(ActionEvent event) {

    }
    @FXML
    public void Button8Clicked(ActionEvent event) {

    }
}

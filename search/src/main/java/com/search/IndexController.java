package com.search;

import com.search.dynamic.DynamicWave;
import com.search.dynamic.FadeTransitionTools;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
}

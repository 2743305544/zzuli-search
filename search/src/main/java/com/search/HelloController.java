package com.search;

import animatefx.animation.*;
import animatefx.util.ParallelAnimationFX;
import animatefx.util.SequentialAnimationFX;

import com.search.constant.SystemConstants;
import com.search.dynamic.DynamicWave;
import com.search.dynamic.FadeTransitionTools;
import com.search.utils.Jumputils;
import eu.iamgio.animated.binding.Animated;
import eu.iamgio.animated.binding.presets.AnimatedOpacity;
import eu.iamgio.animated.binding.presets.AnimatedPrefSize;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label title;

    @FXML
    private MFXButton  ToButton;

    @FXML
    private AnchorPane WelcomePane;

    @FXML
    private AnchorPane twoPane;

    @FXML
    public void ToButtonClicked(ActionEvent event) {
        FadeTransitionTools.fade(WelcomePane);
        Jumputils.Jump(SystemConstants.INDEX_PAGE_NAME,event);
        // 播放动画
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FontIcon icon = new FontIcon(AntDesignIconsOutlined.FORWARD);
        // 假设有这个方法来设置大小
        icon.setIconSize(20);
        ToButton.setGraphic(icon);

        new Thread(() -> {
            Canvas canvas = new Canvas(1020, 200);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    DynamicWave.draw(gc, canvas.getWidth(), canvas.getHeight());
                }
            };
            timer.start();
            twoPane.getChildren().add(canvas);
            WelcomePane.getChildren().addAll(twoPane, title,ToButton);
        }).start();
        new Swing(title).play();
        SequentialAnimationFX sequentialAnimationFX = new SequentialAnimationFX(title, new BounceIn(), new Flash());
        sequentialAnimationFX.setResetOnFinished(true);
        sequentialAnimationFX.play();
    }

}
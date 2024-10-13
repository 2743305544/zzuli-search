package com.search.dynamic;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FadeTransitionTools {
    public static void fade(Pane WelcomePane){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setFromValue(1.0);   // 设置起始透明度为1.0，表示不透明
        fadeTransition.setToValue(0.0);     // 设置结束透明度为0.0，表示透明
        fadeTransition.setCycleCount(1);// 设置循环周期为无限
        fadeTransition.setAutoReverse(true);    // 设置自动反转
        fadeTransition.setNode(WelcomePane);         // 设置动画应用的节点
        fadeTransition.play();
    }
    public static void fadeout(Pane WelcomePane){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setFromValue(0.0);   // 设置起始透明度为1.0，表示不透明
        fadeTransition.setToValue(1.0);     // 设置结束透明度为0.0，表示透明
        fadeTransition.setCycleCount(1);// 设置循环周期为无限
        fadeTransition.setAutoReverse(true);    // 设置自动反转
        fadeTransition.setNode(WelcomePane);         // 设置动画应用的节点
        fadeTransition.play();
    }
}

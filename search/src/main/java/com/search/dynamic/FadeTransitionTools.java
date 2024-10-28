package com.search.dynamic;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FadeTransitionTools {
    public static void fade(Pane WelcomePane){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        // 设置起始透明度为1.0，表示不透明
        fadeTransition.setFromValue(1.0);
        // 设置结束透明度为0.0，表示透明
        fadeTransition.setToValue(0.0);
        // 设置循环周期为无限
        fadeTransition.setCycleCount(1);
        // 设置自动反转
        fadeTransition.setAutoReverse(true);
        // 设置动画应用的节点
        fadeTransition.setNode(WelcomePane);
        fadeTransition.play();
    }
    public static void fadeout(Pane WelcomePane){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        // 设置起始透明度为1.0，表示不透明
        fadeTransition.setFromValue(0.0);
        // 设置结束透明度为0.0，表示透明
        fadeTransition.setToValue(1.0);
        // 设置循环周期为无限
        fadeTransition.setCycleCount(1);
        // 设置自动反转
        fadeTransition.setAutoReverse(true);
        // 设置动画应用的节点
        fadeTransition.setNode(WelcomePane);
        fadeTransition.play();
    }
    public static void fadeInComponent(javafx.scene.Node node,Integer time) {
        // 设置初始透明度为0
        node.setOpacity(0);

        // 创建淡入过渡动画
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(time), node);
        // 从透明开始
        fadeTransition.setFromValue(0);
        // 到完全不透明
        fadeTransition.setToValue(1);
        // 动画播放一次
        fadeTransition.setCycleCount(1);
        // 不反向播放
        fadeTransition.setAutoReverse(false);

        // 开始动画
        fadeTransition.play();
    }
}

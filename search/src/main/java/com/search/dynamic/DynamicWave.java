package com.search.dynamic;

import javafx.scene.canvas.GraphicsContext;

public class DynamicWave {
    // 振幅
    private static final double A = 30;
    // 频率
    private static final double W = 1 / 200.0;
    // 第二波的振幅
    private static final double A2 = 30;
    // 第二波的频率
    private static final double W2 = 1 / 300.0;

    // 第一波相位
    private static double Q = 0;
    // 第二波相位
    private static double Q2 = 0;
    public static void draw(GraphicsContext gc, double width, double height) {
        // 向右偏移100个单位
        double offsetX = 0;
        // 向下偏移50个单位
        double offsetY = 0;
        // 清空画布
        gc.clearRect(0, 0, width, height);

        // 绘制第一条波
        gc.setFill(javafx.scene.paint.Color.rgb(116, 117, 117, 0.8));
        gc.beginPath();
        gc.moveTo(0+offsetX, height / 2+offsetY);
        for (double x = 0; x <= width; x++) {
            double y = A * Math.sin(W * x + Q) + (height / 2)+offsetY;
            gc.lineTo(x+offsetX, y);
        }
        gc.lineTo(width+offsetX, height+offsetY);
        gc.lineTo(0+offsetX, height+offsetY);
        gc.fill();

        // 绘制第二条波
        gc.setFill(javafx.scene.paint.Color.rgb(235, 239, 239, 1));
        gc.beginPath();
        gc.moveTo(0+offsetX, height / 2+offsetY);
        for (double x = 0; x <= width; x++) {
            double y = A2 * Math.sin(W2 * x + Q2) + (height / 2)+offsetY;
            gc.lineTo(x, y);
        }
        gc.lineTo(width+offsetX, height+offsetY);
        gc.lineTo(0+offsetX, height+offsetY);
        gc.fill();

        // 更新相位
        // 第一波的速度
        Q -= 0.01;
        // 第二波的速度
        Q2 -= 0.02;
    }
}

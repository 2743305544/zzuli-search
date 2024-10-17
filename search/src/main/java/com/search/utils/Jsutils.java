package com.search.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Jsutils {
    public static String readJsFileAsString(String fileName)  {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Jsutils.class.getResource("/js/" + fileName)).openStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n"); // 读取每行并添加换行符
            }
        } catch (IOException e) {
            e.printStackTrace(); // 捕获异常并打印
            return null;
        }

        return stringBuilder.toString(); // 返回字符串内容
    }
}

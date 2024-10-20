package com.searchback.service.Impl;

import com.searchback.entry.Content;
import com.searchback.service.ContentService;
import com.searchback.utils.HtmlParseUtil;
import com.searchback.utils.esutils.EsDocumentUtil;
import com.searchback.utils.esutils.EsIndexUtil;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author 34011 shiyi
 */
@Service
public class ContentServiceImpl implements ContentService {

    private static final String logFilePath = "logs";

    private static final String log = Paths.get(logFilePath, "error_log.txt").toString();

//    public static void main(String[] args) {
//        System.out.println(log);
//    }

    @Resource
    private EsIndexUtil esIndexUtil;

    @Resource
    private EsDocumentUtil esDocumentUtil;

    @Resource
    private HtmlParseUtil htmlParseUtil;

    @Override
    public void refreshAllData(WebClient webClient) {
        try {
            esIndexUtil.deleteIndex("Zzuli_data");
        } catch (Exception e) {
            e.printStackTrace();
            logError(e);
        }
        try {
            esIndexUtil.createIndex("Zzuli_data");
        } catch (IOException e) {
            e.printStackTrace();
            logError(e);
        }
        List<Content> contentList = htmlParseUtil.parseHtml(webClient);
        try {
            esDocumentUtil.batchAddDocument("Zzuli_data",contentList);
        } catch (Exception e) {
            e.printStackTrace();
            logError(e);
        }
    }

    private void logError(Exception e) {
        File logDir = new File(logFilePath);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(log, true))) {
            writer.write("错误信息: " + e.getMessage());
            writer.newLine();
            writer.write("堆栈跟踪: ");
            writer.newLine();
            for (StackTraceElement element : e.getStackTrace()) {
                writer.write("\t" + element.toString());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}
}

package com.searchback.service.Impl;

import com.searchback.entry.Content;
import com.searchback.service.ContentService;
import com.searchback.utils.HtmlParseUtil;
import com.searchback.utils.esutils.EsDocumentUtil;
import com.searchback.utils.esutils.EsIndexUtil;
import com.searchback.utils.esutils.EsQueryUtil;
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

    @Resource
    private EsQueryUtil esQueryUtil;

    @Override
    public void refreshAllData(WebClient webClient) {
        try {
            esIndexUtil.deleteIndex("zzuli_data");
        } catch (Exception e) {
            e.printStackTrace();
            logError(e);
        }
        try {
            esIndexUtil.createIndex("zzuli_data");
        } catch (IOException e) {
            e.printStackTrace();
            logError(e);
        }
        List<Content> contentList = htmlParseUtil.parseHtml(webClient);
        System.out.println(contentList);
        try {
            esDocumentUtil.batchAddDocument("zzuli_data",contentList);
        } catch (Exception e) {
            e.printStackTrace();
            logError(e);
        }
    }

    @Override
    public List<Content> getMsg1() {

        try {
            return esDocumentUtil.getAllDocument("zzuli_data",Content.class);
        } catch (IOException e) {
            e.printStackTrace();
            logError(e);
        }
        return null;
    }

    @Override
    public List<Content> SearchMsg1(String searchText) {
        try {
            return esQueryUtil.termQuerySimple("zzuli_data",searchText,"title","date",true,Content.class);
        } catch (IOException e) {
            e.printStackTrace();
            logError(e);
        }
        return null;
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

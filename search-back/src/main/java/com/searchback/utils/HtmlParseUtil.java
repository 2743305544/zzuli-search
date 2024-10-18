package com.searchback.utils;

import com.alibaba.fastjson2.JSON;
import com.searchback.SearchBackApplication;
import com.searchback.entry.Content;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.htmlunit.html.Html;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.server.Cookie;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HtmlParseUtil {
    private static final String firstUrl = "https://info.zzuli.edu.cn/_t961/2464/list1.htm";

    public  List<Content> ParseHtml(WebClient webClient) {
        Document document = null;
        try {
            HtmlPage page = webClient.getPage(firstUrl);
            webClient.waitForBackgroundJavaScript(5000);
//            document = Jsoup.parse(page.getWebResponse().getContentAsString());
            document = Jsoup.parse(page.asXml());
        } catch (Exception e) {
            e.printStackTrace();
//            if (Objects.isNull(document) && e instanceof ) {
//
//            }
        }
        System.out.println(document.toString());
        return null;
    }

}

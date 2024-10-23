package com.searchback.utils;

import com.searchback.constant.UrlConstants;
import com.searchback.entry.Content;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 34011 shiyi
 */
@Component
public class HtmlParseUtil {
    private static final String firstUrl = UrlConstants.ORIGINAL_SEARCH_HTML;

    private static String afterUrl = UrlConstants.ORIGINAL_SEARCH_HTML;

    private static String preUrl = UrlConstants.PRE_URL;
    private  String indexChar = UrlConstants.PAGE_CHAR;

    private final ApplicationContext applicationContext;

    @Resource(name = "ThreadPoolExecutor")
    private ExecutorService executorService;

    private Integer allPageNum;

    @Autowired
    public HtmlParseUtil(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private char replaceChar;

    public  List<Content> parseHtml(WebClient webClient) {
        Document document = null;
        try {
            HtmlPage page = webClient.getPage(firstUrl);
            webClient.waitForBackgroundJavaScript(5000);
//            document = Jsoup.parse(page.getWebResponse().getContentAsString());
            document = Jsoup.parse(page.asXml());
        } catch (Exception e) {
            e.printStackTrace();
//            if (Objects.isNull(document) && e instanceof ) {
//            }
        }
//        System.out.println(document.toString());

        Element first = document.getElementById("warmp").getElementsByClass("xx").getFirst().getElementById("wp_paging_w2").getElementsByClass("all_pages").getFirst();
        System.out.println(first.text());
        allPageNum = Integer.valueOf(first.text());
        List<Content> contentList = crawlAllPage(310);
//        System.out.println(contentList);
        return contentList;
    }
    private List<Content> crawlAllPage(Integer AllPageNum) {
        List<Content> contentList = new ArrayList<>();
        List<Future<List<Content>>> futures = new ArrayList<>();
        for (int target = 1; target <= AllPageNum; target++) {
            final int num = target;
            Callable<List<Content>> task =()->{
                List<Content> tempContentList = new ArrayList<>();
                String newUrl = replaceLastOccurrence(afterUrl, indexChar, String.valueOf(num));
//            System.out.println("111111111111111111111111111111111111111111111111111111"+newUrl);
                Document document = null;
                try (WebClient NewwebClient = applicationContext.getBean(WebClient.class);){
                    HtmlPage page = NewwebClient.getPage(newUrl);
                    NewwebClient.waitForBackgroundJavaScript(5000);
                    document = Jsoup.parse(page.asXml());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Element tbody = document.getElementById("warmp").getElementsByClass("xx").getFirst().getElementsByTag("tbody").getFirst().getElementById("wp_news_w2").getElementsByTag("tbody").getFirst();
                for (Element tr : tbody.getElementsByTag("tr")) {
                    String title = tr.getElementsByTag("a").getFirst().text();
                    String url = tr.getElementsByTag("a").getFirst().attr("href");
                    String Date = tr.getElementsByTag("div").getFirst().text();
                    tempContentList.add(new Content(title, Date, preUrl + url));
                }
                return tempContentList;
            };
            futures.add(executorService.submit(task));
        }
        for (Future<List<Content>> future : futures) {
            try {
                contentList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return contentList;
    }
    private String replaceLastOccurrence(String str, String target, String replacement) {
        int lastIndex = str.lastIndexOf(target);
        if (lastIndex == -1) {
            // 如果没有找到目标字符，返回原字符串
            return str;
        }
        // 取出目标字符之前的部分
        String before = str.substring(0, lastIndex);
        // 取出目标字符之后的部分
        String after = str.substring(lastIndex);
        // 重新组合字符串\
        System.out.println(before + replacement + after.substring(target.length()));
        return before + replacement + after.substring(target.length());
    }

}

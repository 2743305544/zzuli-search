package com.searchback;

import com.searchback.service.ContentService;
import com.searchback.utils.HtmlParseUtil;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchBackApplicationTests {

//    @Resource
//    private DocumentMapper documentMapper;
    @Resource
    private WebClient webClient;

    @Resource
    private HtmlParseUtil htmlParseUtil;

    @Resource
    private ContentService contentService;
    @Test
    void contextLoads() {
//        System.out.println(contentService.SearchMsg1("2024"));
    }

}

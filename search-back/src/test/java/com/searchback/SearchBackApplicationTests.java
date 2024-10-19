package com.searchback;

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

    @Test
    void contextLoads() {
//        Boolean zzuliData = documentMapper.createIndex("zzuliData");
//        System.out.println(zzuliData);
        htmlParseUtil.ParseHtml(webClient);
        webClient.close();
    }

}

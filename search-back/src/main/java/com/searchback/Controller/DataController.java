package com.searchback.Controller;

import com.searchback.ResponseResult;
import com.searchback.entry.Content;
import com.searchback.service.ContentService;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author 34011 shiyi
 */
@RestController
@RequestMapping("/api")
public class DataController {

    @Resource
    private WebClient webClient;

    @Resource
    private ContentService contentService;

    @GetMapping("/refreshAllData")
    public ResponseResult refreshAllData() {
        contentService.refreshAllData(webClient);
        webClient.close();
        return ResponseResult.okResult();
    }
    @GetMapping("/getMsg1")
    public ResponseResult<List<Content>> getMsg1() {
        List<Content> contentList = contentService.getMsg1();
        return ResponseResult.okResult(contentList);
    }
    @GetMapping("/searchMsg1")
    public ResponseResult<List<Content>> searchMsg1(@RequestParam("searchText") String searchText) {
        List<Content> contentList = contentService.SearchMsg1(searchText);
        return ResponseResult.okResult(contentList);
    }
}

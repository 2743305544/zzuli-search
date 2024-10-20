package com.searchback.Controller;

import com.searchback.ResponseResult;
import com.searchback.service.ContentService;
import jakarta.annotation.Resource;
import org.htmlunit.WebClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}

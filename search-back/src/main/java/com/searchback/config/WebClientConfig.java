package com.searchback.config;

import org.htmlunit.BrowserVersion;
import org.htmlunit.NicelyResynchronizingAjaxController;
import org.htmlunit.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 34011 shiyi
 */
@Configuration
public class WebClientConfig {
    @Bean()
    @Scope ("prototype")
    public  WebClient getWebClient() {
        //创建火狐浏览器 2.23版本是FIREFOX_45 new不写参数是默认浏览器
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        //（屏蔽)css 因为css并不影响我们抓取数据 反而影响网页渲染效率
        webClient.getOptions().setCssEnabled(false);
        //（屏蔽)异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //（屏蔽)日志
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //加载js脚本
        webClient.getOptions().setJavaScriptEnabled(true);
        //设置超时时间
        webClient.getOptions().setTimeout(50000);
        //设置ajax
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        return webClient;
    }
}

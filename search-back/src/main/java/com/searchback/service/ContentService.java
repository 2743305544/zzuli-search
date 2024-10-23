package com.searchback.service;

import com.searchback.entry.Content;
import org.htmlunit.WebClient;

import java.util.List;

/**
 * @author 34011 shiyi
 */
public interface ContentService {
    void refreshAllData(WebClient webClient);

    List<Content> getMsg1();

    List<Content> SearchMsg1(String searchText);
}

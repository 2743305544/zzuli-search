package com.searchback.config;

import com.searchback.constant.SystemConstants;
import com.searchback.entry.Content;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.*;

/**
 * @author 34011 shiyi
 */
@Configuration
public class ExecutorConfig {
    @Bean("ThreadPoolExecutor")
    public ExecutorService getExecutorService() {
        return new ThreadPoolExecutor(SystemConstants.AVAILABLE_PROCESSORS,
                SystemConstants.AVAILABLE_PROCESSORS * 2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20000));
    }
}

package com.gjk.dynamic_thread_pool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjk.dynamic_thread_pool.util.DynamicThreadPoolUtil;

/**
 * DynamicThreadPoolController
 *
 * @author: gaojiankang
 * @date: 2023/2/22 9:19
 * @description:
 */
@RefreshScope
@RestController
@RequestMapping("/dynamic/threadPool")
public class DynamicThreadPoolController {

    @Value("test")
    private String test;

    @Autowired
    private DynamicThreadPoolUtil dynamicThreadPoolUtil;


    /**
     * 打印当前线程池的状态
     */
    @GetMapping("/print")
    public String printThreadPoolStatus() {
        return dynamicThreadPoolUtil.printThreadPoolStatus();
    }

    /**
     * 给线程池增加任务
     *
     * @param count
     */
    @GetMapping("/add")
    public String dynamicThreadPoolAddTask(int count) {
        dynamicThreadPoolUtil.dynamicThreadPoolAddTask(count);
        return String.valueOf(count);
    }

    /**
     * test refreshScope
     */
    @GetMapping("/test")
    public String testRefreshScope() {
        return String.valueOf(test);
    }
}

package com.gjk.dynamic_thread_pool.util;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;
import com.gjk.dynamic_thread_pool.config.DynamicThreadPoolConfig;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * DynamicThreadPoolUtil
 *
 * @author: gaojiankang
 * @date: 2023/2/22 9:30
 * @description:
 */
@Component
public class DynamicThreadPoolUtil implements InitializingBean {

    @Autowired
    private DynamicThreadPoolConfig dynamicThreadPoolConfig;

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    private static ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化线程池
        threadPoolExecutor = new ThreadPoolExecutor(
                dynamicThreadPoolConfig.getCoreSize(),
                dynamicThreadPoolConfig.getMaxSize(),
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20),
                new ThreadFactoryBuilder().setNameFormat("DynamicThreadPoolUtil_threadPoolExecutor_%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        //nacos配置变更监听
        nacosConfigManager.getConfigService().addListener("dynamic-thread-pool.properties", nacosConfigProperties.getGroup(),
                new PropertiesListener() {

                    @Override
                    public void innerReceive(Properties properties) {
                        String coreSize = properties.getProperty("dynamic.thread.pool.coreSize");
                        String maxSize = properties.getProperty("dynamic.thread.pool.maxSize");
                        changeThreadPoolConfig(Integer.parseInt(coreSize), Integer.parseInt(maxSize));
                    }
                });
    }


    /**
     * 打印当前线程池的状态
     */
    public String printThreadPoolStatus() {
        return String.format("core_size:%s,thread_current_size:%s;" +
                        "thread_max_size:%s;queue_current_size:%s,total_task_count:%s", threadPoolExecutor.getCorePoolSize(),
                threadPoolExecutor.getActiveCount(), threadPoolExecutor.getMaximumPoolSize(), threadPoolExecutor.getQueue().size(),
                threadPoolExecutor.getTaskCount());
    }

    /**
     * 给线程池增加任务
     *
     * @param count
     */
    public void dynamicThreadPoolAddTask(int count) {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(finalI);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    /**
     * 修改线程池核心参数
     *
     * @param coreSize
     * @param maxSize
     */
    private void changeThreadPoolConfig(int coreSize, int maxSize) {
        threadPoolExecutor.setCorePoolSize(coreSize);
        threadPoolExecutor.setMaximumPoolSize(maxSize);
    }
}

package com.gjk.dynamic_thread_pool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * DynamicThreadPoolConfig
 *
 * @author: gaojiankang
 * @date: 2023/2/22 9:23
 * @description:
 */
@RefreshScope
@Data
@Configuration
public class DynamicThreadPoolConfig {

    @Value("${dynamic.thread.pool.coreSize:2}")
    private Integer coreSize;

    @Value("${dynamic.thread.pool.maxSize:4}")
    private Integer maxSize;
}

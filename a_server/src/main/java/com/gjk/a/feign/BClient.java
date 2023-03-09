package com.gjk.a.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.gjk.a.feign.fallback.BClientFallBack;
import com.gjk.a.feign.fallback.FeignConfiguration;

/**
 * BClient
 *
 * @author: gaojiankang
 * @date: 2022/9/20 10:51
 * @description:
 *
 * Sentinel 集成 Feign :
 * 1、添加配置   feign.sentinel.enabled=true
 * 2、@FeignClient 注解中的所有属性，Sentinel 都做了兼容。在@FeignClient添加 fallback属性和FeignConfiguration属性
 *        当接口请求失败时便会调用失败类里的该方法
 * */
@FeignClient(name = "server-b", fallback = BClientFallBack.class , configuration = FeignConfiguration.class)
@Component
public interface BClient {

    @GetMapping("/b/add")
     String add();
}

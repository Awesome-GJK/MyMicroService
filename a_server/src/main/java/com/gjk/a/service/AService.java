package com.gjk.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gjk.a.feign.BClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AService
 *
 * @author: gaojiankang
 * @date: 2022/9/28 14:34
 * @description:
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AService {

    private final BClient bClient;

    /**
     * @SentinelResource 注解用来标识资源是否被限流、降级。上述例子上该注解的属性 sayHello 表示资源名。
     */
    @SentinelResource(value = "hello")
    public void hello(){
        log.info("Hello World!!!");
        //bClient.add();
    }
}

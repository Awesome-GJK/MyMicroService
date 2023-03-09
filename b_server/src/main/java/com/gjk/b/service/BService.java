package com.gjk.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gjk.b.feign.AClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BService
 *
 * @author: gaojiankang
 * @date: 2022/9/28 15:06
 * @description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BService {

    private final AClient aClient;

    @SentinelResource(value = "hello", blockHandler = "helloBlock")
    public void hello() {
        log.info("Hello World!!!");
        String add = aClient.add();
        log.info(add);
    }

    public String helloBlock(BlockException e) {
        log.info("你已被限流");
        return "你已被限流";
    }
}

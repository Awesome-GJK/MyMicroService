package com.gjk.a.feign.fallback;

import com.gjk.a.feign.BClient;

import lombok.extern.slf4j.Slf4j;

/**
 * BClientFallBack
 *
 * @author: gaojiankang
 * @date: 2022/9/28 14:26
 * @description:
 */
@Slf4j
public class BClientFallBack implements BClient {
    @Override
    public String add() {
        log.info("调用BClient /b/add失败，已被限流");
        return "调用BClient /b/add失败，已被限流";
    }
}

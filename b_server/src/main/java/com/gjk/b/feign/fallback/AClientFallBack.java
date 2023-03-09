package com.gjk.b.feign.fallback;

import com.gjk.b.feign.AClient;

import lombok.extern.slf4j.Slf4j;

/**
 * AClientFallBack
 *
 * @author: gaojiankang
 * @date: 2022/9/28 15:09
 * @description:
 */
@Slf4j
public class AClientFallBack implements AClient {
    @Override
    public String add() {
        return "调用AClient /a/add失败,已被限流";
    }
}

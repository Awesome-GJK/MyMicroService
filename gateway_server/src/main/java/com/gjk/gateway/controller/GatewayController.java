package com.gjk.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GatewayController
 *
 * @author: gaojiankang
 * @date: 2022/9/26 19:40
 * @description:
 */
@RestController
@RefreshScope
public class GatewayController {

    @Value("${user.name}")
    private String name;


    @GetMapping("getName")
    public String getName(){
        return name;
    }
}

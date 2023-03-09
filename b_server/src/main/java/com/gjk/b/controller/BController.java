package com.gjk.b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjk.b.service.BService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BController
 *
 * @author: gaojiankang
 * @date: 2022/9/19 20:23
 * @description:
 */
@RefreshScope
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BController {

    private final BService bService;

    @Value("${user.id}")
    private String id;
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private String age;

    @GetMapping("/add")
    public String add() {
        log.info("调用/b/add接口");
        bService.hello();
        return "userId:{" + id + "}, userName:{" + name + "}, userAge:{" + age + "}";
    }
}

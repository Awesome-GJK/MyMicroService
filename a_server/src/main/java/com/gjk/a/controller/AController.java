package com.gjk.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.gjk.a.entity.User;
import com.gjk.a.service.AService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AController
 *
 * @author: gaojiankang
 * @date: 2022/9/19 20:19
 * @description:
 */
@RefreshScope
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AController {

    private final AService aService;


    @Value("${user.id}")
    private String userId;

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private String userAge;

    @GetMapping("/add")
    public String add(){
        log.info("调用/a/add接口");
        aService.hello();
        return "userId:{" + userId + "}, userName:{" + userName + "}, userAge:{" + userAge + "}";

    }

    @PostMapping("/user/save")
    public String save(@RequestBody @Validated User user){
        System.out.println(JSON.toJSONString(user));
        System.out.println("yes");
        return JSON.toJSONString(user);
    }
}

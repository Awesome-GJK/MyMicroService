package com.gjk.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AApplication
 *
 * @author: gaojiankang
 * @date: 2022/9/7 17:43
 * @description:
 */
@EnableFeignClients
@SpringBootApplication
public class AApplication {

    public static void main(String[] args) {
        SpringApplication.run(AApplication.class,args);
    }
}

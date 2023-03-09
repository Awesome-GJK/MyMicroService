package com.gjk.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * BApplication
 *
 * @author: gaojiankang
 * @date: 2022/9/19 19:38
 * @description:
 */
@SpringBootApplication
@EnableFeignClients
public class BApplication {

    public static void main(String[] args) {
        SpringApplication.run(BApplication.class, args);
    }
}

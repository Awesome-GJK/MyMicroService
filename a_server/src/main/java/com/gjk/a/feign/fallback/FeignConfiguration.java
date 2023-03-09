package com.gjk.a.feign.fallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfiguration
 *
 * @author: gaojiankang
 * @date: 2022/9/28 14:28
 * @description:
 */

@Configuration
public class FeignConfiguration {


    @Bean
    public BClientFallBack bClientFallBack(){
        return new BClientFallBack();
    }
}

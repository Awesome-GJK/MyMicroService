package com.gjk.b.feign.fallback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfiguration
 *
 * @author: gaojiankang
 * @date: 2022/9/28 15:10
 * @description:
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public AClientFallBack aClientFallBack(){
        return new AClientFallBack();
    }
}

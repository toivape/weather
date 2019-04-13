package com.example.weather

import feign.Logger
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@Configuration
@EnableFeignClients
class FeignConfig{
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.HEADERS
    }
}
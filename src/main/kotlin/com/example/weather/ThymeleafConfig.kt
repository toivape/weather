package com.example.weather

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Description
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Configuration
class ThymeleafConfig : WebMvcConfigurer {

    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    fun templateResolver(): ClassLoaderTemplateResolver = with(ClassLoaderTemplateResolver()) {
        prefix = "templates/"
        isCacheable = false
        suffix = ".html"
        setTemplateMode("HTML")
        characterEncoding = "UTF-8"
        this
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    fun templateEngine(): SpringTemplateEngine  = with(SpringTemplateEngine()){
        setTemplateResolver(templateResolver())
       this
    }

    @Bean
    @Description("Thymeleaf view resolver")
    fun viewResolver(): ViewResolver = with(ThymeleafViewResolver()) {
        templateEngine = templateEngine()
        characterEncoding = "UTF-8"
        this
    }

}
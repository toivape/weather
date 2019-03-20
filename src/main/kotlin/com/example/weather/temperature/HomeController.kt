package com.example.weather.temperature

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

private val logger = KotlinLogging.logger { }

@Controller
class HomeController {

    @GetMapping("/")
    fun home(model: Model):String {
        logger.info { "GET Home request" }
        return "index"
    }

}
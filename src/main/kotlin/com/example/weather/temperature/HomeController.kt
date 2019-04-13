package com.example.weather.temperature

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger { }

@Controller
class HomeController(private val weatherService: WeatherService) {

    @GetMapping("/")
    fun home(model: Model):String {
        logger.info { "GET Home request" }
        model.addAttribute("weatherStation", KAISANIEMI)
        model.addAttribute("temperatures", weatherService.getDailyTemperaturesForLastSevenDays(KAISANIEMI))
        return "index"
    }

}
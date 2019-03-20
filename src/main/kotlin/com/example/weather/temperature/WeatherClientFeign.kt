package com.example.weather.temperature

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "weather", url = "http://127.0.0.1:9001", path = "/v1/weather/temperature/")
interface WeatherClientFeign{

    @GetMapping("daily/fmisid/{fmisid}/year/{year}/month/{month}")
    fun getDailyTemperaturesForMonth(
            @PathVariable("fmisid") fmisid:Int,
            @PathVariable("year")   year:Int,
            @PathVariable("month")  month:Int):DailyWeather
}
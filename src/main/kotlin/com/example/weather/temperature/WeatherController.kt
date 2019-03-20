package com.example.weather.temperature

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class WeatherController (private val weatherService: WeatherService){

    private val log = LoggerFactory.getLogger(WeatherController::class.java)

    @GetMapping("/v1/weather/temperature/daily/fmisid/{fmisid}/year/{year}/month/{month}")
    fun getDailyTemperaturesForMonth(
            @PathVariable(value = "fmisid") fmisid: Int,
            @PathVariable(value = "year") year: Int,
            @PathVariable(value = "month") month: Int):DailyWeather{
        log.info("getDailyTemperaturesForMonth {}, {}, {}", fmisid, year, month)
        return weatherService.getDailyTemperaturesForMonth(fmisid, year, month)
    }
}

data class DayTemperatureSeries(val date: Date, val hourlyTemperature: DoubleArray, val avgTemperature: Double)

data class DailyWeather(val weatherStationName: String, val dayTemperatureSeries: List<DayTemperatureSeries>)
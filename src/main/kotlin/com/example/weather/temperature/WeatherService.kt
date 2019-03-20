package com.example.weather.temperature

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class WeatherService(private val weatherClient : WeatherClientFeign) {

    private val log = LoggerFactory.getLogger(WeatherService::class.java)

    fun getDailyTemperaturesForMonth(fmisid: Int, year:Int, month:Int): DailyWeather{
        log.info("getDailyTemperaturesForMonth {}, {}, {}", fmisid, year, month)
        return weatherClient.getDailyTemperaturesForMonth(fmisid, year, month)
    }
}


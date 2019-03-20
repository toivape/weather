package com.example.weather.temperature

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDate

private val logger = KotlinLogging.logger { }

data class DayTemperatureSeries(val date: LocalDate, val hourlyTemperature: DoubleArray, val avgTemperature: Double)
data class DailyWeather(val weatherStationName: String, val dayTemperatureSeries: List<DayTemperatureSeries>)

@Service
class WeatherService {

    fun getDailyTemperaturesForLastSevenDays(weatherStation:WeatherStation): DailyWeather {
        var date = LocalDate.now().minusDays(7)
        val weekWeather = mutableListOf<DayTemperatureSeries>()
        for (i in 1..7){
            weekWeather.add(DayTemperatureSeries(date, hourlyTemperature = temperatures, avgTemperature = 12.2))
            date = date.plusDays(1)
        }

        return DailyWeather(weatherStation.name, weekWeather)
    }

    val temperatures = doubleArrayOf(1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.0, 11.1, 12.2, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.0, 11.1, 12.2)

}


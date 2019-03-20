package com.example.weather.sun

import java.time.LocalDate
import java.time.LocalDateTime
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection


fun calculateSunriseSeriesToClipboard(latitude:Double, longitude:Double, year:Int){
    var date = LocalDate.of(year, 1, 1)
    fun calculateSeries() = mutableListOf<Pair<LocalDateTime, LocalDateTime>>().apply{
        for (d in 1..365){
            add(Pair(
                getSunriseTime(date, latitude, longitude),
                getSunriseTime(date, latitude, longitude, isSunrise = false)
            ))
            date = date.plusDays(1)
        }
    }

    val series = buildString { calculateSeries().forEach { append(it.first, ";", it.second, "\n")} }
    copyToClipboard(series)
}

private fun copyToClipboard(s:String){
    Toolkit.getDefaultToolkit()
            .systemClipboard
            .setContents(StringSelection(s), null)
}


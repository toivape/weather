package com.example.weather.sun

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SunriseTest{

    private val testDate = LocalDate.of (2019, 3, 15)
    private val latHelsinki = 60.1699
    private val lngHelsinki = 24.9384

    @Test
    fun `Sun rises in Helsinki at 0638 hours on 2019-03-14`(){
        val sunriseTime = getSunriseTime(testDate, latHelsinki, lngHelsinki).toLocalTime()
        assertThat(sunriseTime).isEqualToIgnoringSeconds(LocalTime.of(6,38, 36))
    }

    @Test
    fun `Both sunrises return same value`(){
        val sunriseA = getSunriseTime(testDate, latHelsinki, lngHelsinki).toLocalTime()
        val sunriseC = SunriseCee.getSunriseTime(testDate, latHelsinki, lngHelsinki)
        assertThat(sunriseA).isEqualTo(sunriseC)
    }

    @Test
    fun `Sun sets in Helsinki at 1820 hours on 2019-03-14`(){
        val sunriseTime = getSunriseTime(testDate, latHelsinki,lngHelsinki, isSunrise = false).toLocalTime()
        assertThat(sunriseTime).isEqualTo(LocalTime.of(18,21, 27))
    }

    val latRovaniemi = 66.5039
    val lngRovaniemi = 25.7294

    @Test
    fun `Sun rises in Rovaniemi at 0639 hours on 2019-03-15`(){
        val sunriseTime = getSunriseTime(testDate, latRovaniemi,lngRovaniemi).toLocalTime()
        assertThat(sunriseTime).isEqualTo(LocalTime.of(6,38, 51))
    }

    @Test
    fun `Sun sets in Rovaniemi at 1815 hours on 2019-03-15`(){
        val sunriseTime = getSunriseTime(testDate, latRovaniemi,lngRovaniemi, isSunrise = false).toLocalTime()
        assertThat(sunriseTime).isEqualTo(LocalTime.of(18,15, 19))
    }

    @Ignore
    @Test
    fun `calculate sunrise series for helsinki`(){
        calculateSunriseSeriesToClipboard(latHelsinki, lngHelsinki, 2019)
    }

    @Test
    fun `where are my seconds`(){
        val date = LocalDate.of(2019, 12, 7)
        val sunriseTime = getSunriseTime(date, latHelsinki, lngHelsinki).toLocalTime()
        println(sunriseTime)
        assertThat(sunriseTime.second).isEqualTo(0)
    }
}
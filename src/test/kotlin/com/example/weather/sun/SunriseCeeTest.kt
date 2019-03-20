package com.example.weather.sun

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import java.time.LocalDate

class SunriseCeeTest {

    private val testDate = LocalDate.of(2019, 3, 14)
    private val latHelsinki = 60.1699
    private val lngHelsinki = 24.9384

    @Ignore
    @Test
    fun `calc day of year`(){
        val dayOfYear = calculateDayOfYear(2019, 3, 14)
        val expectedDayOfYear = LocalDate.now().dayOfYear
        assertThat(dayOfYear).isEqualTo(expectedDayOfYear)
    }

    @Test
    fun `time of day is returned`(){

        val result = SunriseCee.getSunriseTime(testDate, latHelsinki, lngHelsinki)
        println("SUNRISE IS AT $result")
    }

}
package com.example.weather.fmi

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


// TODO Käytä UTC aikaa
fun getStartOfYesterday(): LocalDateTime = LocalDate.now().minusDays(1).atTime(LocalTime.MIN)

// TODO Käytä UTC aikaa
fun getEndOfYesterday(): LocalDateTime = LocalDate.now().minusDays(1).atTime(23, 59, 59)

private val logger = KotlinLogging.logger { }

@Service
class FmiService(private val fmiClient:FmiFeignClient, @Value("\${fmi.apikey}") private val fmiApiKey: String){

    fun getHourlyTemperaturesYesterday(fmisid:Int):String {
        return fmiClient.getHourlyTemperatures(fmiApiKey, fmisid, getStartOfYesterday(), getEndOfYesterday())
    }

}


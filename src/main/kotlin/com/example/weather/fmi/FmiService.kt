package com.example.weather.fmi

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.*

internal val TZ_UTC:ZoneId = ZoneId.of("UTC")

fun LocalDateTime.toUTC() : LocalDateTime = this.atZone(ZoneId.systemDefault()).withZoneSameInstant(TZ_UTC).toLocalDateTime()

fun getStartOfYesterday(): LocalDateTime = LocalDate.now().minusDays(1).atStartOfDay().toUTC()

fun getEndOfYesterday(): LocalDateTime = LocalDate.now().minusDays(1).atTime(23, 59, 59).toUTC()

private val logger = KotlinLogging.logger { }

@Service
class FmiService(private val fmiClient:FmiFeignClient, @Value("\${fmi.apikey}") private val fmiApiKey: String){

    fun getHourlyTemperaturesYesterday(fmisid:Int):String {
        return fmiClient.getHourlyTemperatures(fmiApiKey, fmisid, getStartOfYesterday(), getEndOfYesterday())
    }

}


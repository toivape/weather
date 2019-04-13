package com.example.weather.fmi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDateTime

@FeignClient(name = "weather", url = "\${fmi.apiBase}")
interface FmiFeignClient{

    @GetMapping("/fmi-apikey/{fmiApiKey}/wfs?request=getFeature&storedquery_id=fmi::observations::weather::simple&timestep=60&parameters=t2m&fmisid={fmisid}&starttime={startTime}&endtime={endTime}", consumes = ["application/xml"])
    fun getHourlyTemperatures(
            @PathVariable("fmiApiKey") fmiApiKey:String,
            @PathVariable("fmisid") fmisid:Int,
            @PathVariable("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) startTime: LocalDateTime,
            @PathVariable("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) endTime: LocalDateTime
    ):String
}
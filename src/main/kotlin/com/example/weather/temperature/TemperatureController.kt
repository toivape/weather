package com.example.weather.temperature

import com.example.weather.fmi.FmiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/temperature/")
class TemperatureController(private val fmiService: FmiService){

    @GetMapping("yesterday/fmisid/{fmisid}", produces = ["application/xml"])
    fun getYesterdayTemperatures(@PathVariable("fmisid") fmisid:Int):String = fmiService.getHourlyTemperaturesYesterday(fmisid)
}
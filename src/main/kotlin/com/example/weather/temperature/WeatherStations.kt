package com.example.weather.temperature

data class WeatherStation(val name:String, val fmisid:Int, val lat:Double, val lng:Double)

val KAISANIEMI = WeatherStation("Helsinki Kaisaniemi", 100971, 60.18,24.94)
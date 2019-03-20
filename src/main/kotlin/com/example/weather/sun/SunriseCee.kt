package com.example.weather.sun

import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.*


object SunriseCee {

    private val civilZenith = 96.0

    fun getSunriseTime(date: LocalDate, la: Double, lo: Double, isSunrise: Boolean = true): LocalTime {
        val day = date.dayOfYear

        val zenith = 90.83333333333333
        val D2R = PI / 180
        val R2D = 180 / PI

        // convert the longitude to hour value and calculate an approximate time
        val lnHour = lo / 15

        // convert the longitude to hour value and calculate an approximate time
        val t = if (isSunrise)
            day + ((6 - lnHour) / 24)
        else
            day + ((18 - lnHour) / 24)

        //calculate the Sun's mean anomaly
        val M = 0.9856 * t - 3.289

        //calculate the Sun's true longitude
        var L = M + 1.916 * sin(M * D2R) + 0.020 * sin(2 * M * D2R) + 282.634
        if (L > 360) {
            L = L - 360
        } else if (L < 0) {
            L = L + 360
        }

        //calculate the Sun's right ascension
        var RA = R2D * atan(0.91764 * tan(L * D2R))
        if (RA > 360) {
            RA = RA - 360
        } else if (RA < 0) {
            RA = RA + 360
        }

        //right ascension value needs to be in the same qua
        val Lquadrant = floor(L / 90) * 90
        val RAquadrant = floor(RA / 90) * 90
        RA = RA + (Lquadrant - RAquadrant)
        //right ascension value needs to be converted into hours
        RA = RA / 15


        //calculate the Sun's declination
        val sinDec = 0.39782 * sin(L * D2R)
        val cosDec = cos(asin(sinDec))
        //calculate the Sun's local hour angle
        val cosH = (cos(zenith * D2R) - sinDec * sin(la * D2R)) / (cosDec * cos(la * D2R))
        var H = if (isSunrise) {
            360 - R2D * acos(cosH)
        } else {
            R2D * Math.acos(cosH)
        }
        H = H / 15

        //calculate local mean time of rising/setting
        val T = H + RA - 0.06571 * t - 6.622

        //adjust back to UTC
        var UT = T - lnHour
        if (UT > 24) {
            UT = UT - 24
        } else if (UT < 0) {
            UT = UT + 24
        }

        //convert UT value to local time zone of latitude/longitude
        //val offset = (lo / 15).toInt() // estimate utc correction
        //val localT = UT + offset // -5 for baltimore
        val localT = UT + 2

        //convert to seconds
        val seconds = (localT * 3600).toInt()
        println("Total seconds $seconds")

        val sec = seconds % 60
        val minutes = seconds % 3600 / 60
        var hours = seconds % 86400 / 3600
        hours = hours % 12

        val typeText = if (isSunrise) "SUNRISE" else "SUNSET"

        println("$typeText TIME IS $hours:$minutes:$sec")

        return LocalTime.of(hours, minutes, sec)
    }

}


fun calculateDayOfYear(xyear:Int, xmonth:Int, xday:Int) : Int{

// calculate the day of the year
    //  N1 = floor(275 * month / 9)
    val xxN1 = floor(275 * xmonth / 9f)
    //  N2 = floor((month + 9) / 12)
    val xxN2 = floor((xmonth + 9) / 12f)
    //  N3 = (1 + floor((year - 4 * floor(year / 4) + 2) / 3))
    val xxN3 = 1 + floor((xyear - 4 * floor(xyear / 4f) + 2) / 3)
    //  N = N1 - (N2 * N3) + day - 30
    val day = xxN1 - xxN2 * xxN3 + xday - 30

    return day.toInt()
}
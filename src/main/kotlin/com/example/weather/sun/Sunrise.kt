package com.example.weather.sun

import java.time.*
import kotlin.math.*


/**
 * Calculate the time of sunrise or sunset in given coordinates.
 */
//object Sunrise {

    //private val civilZenith = 96.0
    private val zenith = 90.83333333333333
    private val D2R = PI / 180
    private val R2D = 180 / PI

    fun getSunriseTime(date: LocalDate, latitude: Double, longitude: Double, isSunrise: Boolean = true): LocalDateTime {

        val dayOfYear = date.dayOfYear

        // convert the longitude to hour value and calculate an approximate time
        val longitudeHour = longitude / 15

        // convert the longitude to hour value and calculate an approximate time
        val t = getApproximateTime(longitudeHour, dayOfYear, isSunrise)

        val L = getSunTrueLongitude(t)

        //calculate the Sun's right ascension
        val RA = getSunRightAscension(L)

        //calculate the Sun's declination
        val H = getSunDeclination(L, latitude, isSunrise)

        //calculate local mean time of rising/setting
        val T = H + RA - 0.06571 * t - 6.622

        //adjust back to UTC
        val utcTime = toUTC(T, longitudeHour)

        //convert to seconds
        val utcSeconds = (utcTime * 3600).toInt()
        println("Total seconds $utcSeconds")

        val seconds = utcSeconds % 60
        val minutes = utcSeconds % 3600 / 60
        var hours = utcSeconds % 86400 / 3600
        hours %= 12
        if (!isSunrise) hours+=12

        val typeText = if (isSunrise) "SUNRISE" else "SUNSET"
        println("$typeText TIME IS UTC $hours:$minutes:$seconds")

        val time = toFinnishTime(date, hours, minutes, seconds)
        println("$typeText TIME IS $time")

        return time
    }

    private fun getApproximateTime(lnHour:Double, day:Int, isSunrise: Boolean) =
        if (isSunrise)
            day + ((6 - lnHour) / 24)
        else
            day + ((18 - lnHour) / 24)

    private fun getSunTrueLongitude(t: Double):Double{
        //calculate the Sun's mean anomaly
        val M = 0.9856 * t - 3.289

        //calculate the Sun's true longitude
        val L = M + 1.916 * sin(M * D2R) + 0.020 * sin(2 * M * D2R) + 282.634
        return when{
            L > 360 -> L - 360
            L < 0 -> L + 360
            else -> L
        }
    }

    private fun getSunRightAscension(L:Double): Double{
        var RA = R2D * atan(0.91764 * tan(L * D2R))
        if (RA > 360) {
            RA -= 360
        } else if (RA < 0) {
            RA += 360
        }
        //right ascension value needs to be in the same qua
        val Lquadrant = floor(L / 90) * 90
        val RAquadrant = floor(RA / 90) * 90
        RA += (Lquadrant - RAquadrant)
        //right ascension value needs to be converted into hours
        return RA / 15
    }

    private fun getSunDeclination(L:Double, la: Double, isSunrise: Boolean):Double{
        //calculate the Sun's declination
        val sinDec = 0.39782 * sin(L * D2R)
        val cosDec = cos(asin(sinDec))
        //calculate the Sun's local hour angle
        val cosH = (cos(zenith * D2R) - sinDec * sin(la * D2R)) / (cosDec * cos(la * D2R))
        val H = if (isSunrise) {
            360 - R2D * acos(cosH)
        } else {
            R2D * Math.acos(cosH)
        }
        return H / 15
    }

    private fun toUTC(T:Double, lnHour:Double) : Double {
        val UT = T - lnHour
        return when {
            UT > 24 -> UT - 24
            UT < 0 -> UT + 24
            else -> UT
        }
    }

    private val HELSINKI = ZoneId.of("Europe/Helsinki")

    private fun toFinnishTime(date:LocalDate, hours:Int, minutes:Int, seconds:Int) =
            ZonedDateTime.of(date, LocalTime.of(hours, minutes, seconds), ZoneOffset.UTC)
                    .withZoneSameInstant(HELSINKI)
                    .toLocalDateTime()


//}
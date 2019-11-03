package com.weather.testapp.utils.converter

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TimeConverter {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun EEEEddMMMMyyyy(date:String):String{
        val d1 = format.parse(date)

        val local = Locale("in", "id")
        val simpleDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", local)

        return simpleDateFormat.format(d1!!)
    }

    fun HHamPM(date:String):String{
        val d1 = format.parse(date)
        val nFormat = SimpleDateFormat("HH a")
        return nFormat.format(d1!!)
    }

    fun EEEE(date: String): String {
        val d1 = format.parse(date)

        val local = Locale("in", "id")
        val simpleDateFormat = SimpleDateFormat("EEEE", local)

        return simpleDateFormat.format(d1!!)
    }

    fun YYYYmmddHHmmssToString(date: String): Date {
        val d1 =format.parse(date)
        return d1!!
    }

    fun CurrentDateTime():String{
        val time = System.currentTimeMillis()
        try{
            val date = Date(time)

            val a = format.format(date)
            return a
        }catch (e: Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun CurrentTime():String{
        val time = System.currentTimeMillis()
        val format = SimpleDateFormat("HH a")
        return format.format(Date(time))
    }
}
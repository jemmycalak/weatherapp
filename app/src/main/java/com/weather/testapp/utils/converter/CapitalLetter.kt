package com.weather.testapp.utils.converter

class CapitalLetter {
    fun CapsSentences(s:String):String{
        val splits = s.toLowerCase().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        for (i in splits.indices) {
            val eachWord = splits[i]
            if (i > 0 && eachWord.length > 0) {
                sb.append(" ")
            }
            val cap = eachWord.substring(0, 1).toUpperCase() + eachWord.substring(1)
            sb.append(cap)
        }
        return sb.toString()
    }
}
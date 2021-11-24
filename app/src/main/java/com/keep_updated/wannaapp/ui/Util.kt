package com.keep_updated.wannaapp.ui

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


object Util {


    @SuppressLint("SimpleDateFormat")
    fun getRequiredDateFormat(time: String): String {

        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
        val outputPattern1 = "MMM dd, yyyy HH:mm aaa"
        val outputPattern2 = "EEEE"
        val inputFormat = SimpleDateFormat(inputPattern)

        val date = inputFormat.parse(time.substring(0, time.length - 1))

        val outputFormat1 = SimpleDateFormat(outputPattern1)
        val outputFormat2 = SimpleDateFormat(outputPattern2)

        var str1 = ""
        var str2 = ""
        try {
            str1 = outputFormat1.format(date)
            str2 = outputFormat2.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "${str2.uppercase()}, $str1"
    }
}
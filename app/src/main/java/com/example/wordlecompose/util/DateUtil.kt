package com.example.wordlecompose.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateHandler {
    fun getDateSum(): Int {
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        return (month * day) - month
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        return Calendar.getInstance().time.run {
            SimpleDateFormat("dd/MM/yyyy").format(this)
        }
    }
}
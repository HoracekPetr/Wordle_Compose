package com.example.wordlecompose.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

data class GameDates(
    val currentDate: String? = null,
    val lastDate: String? = null
)

object DateHandler {
    fun getDateSum(): Int {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        return ((year * month + day)/year) * (month / day)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        return Calendar.getInstance().time.run {
            SimpleDateFormat("dd/MM/yyyy").format(this)
        }
    }
}
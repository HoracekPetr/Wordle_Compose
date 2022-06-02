package com.example.wordlecompose.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateHandler {
    fun getDateSum(): Int {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        Log.d("DATE SUM", "is ${year + month + day}")
        return year + month + day
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        return Calendar.getInstance().time.run {
            SimpleDateFormat("dd/MM/yyyy").format(this)
        }
    }
}
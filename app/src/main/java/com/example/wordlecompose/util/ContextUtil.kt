package com.example.wordlecompose.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.AppPreferences
import com.example.AppPreferencesSerializer
import java.text.SimpleDateFormat
import java.util.*

val Context.dataStore by dataStore(
    fileName = "app-preferences.json",
    serializer = AppPreferencesSerializer
)

object DateHandler {
    suspend fun updateDate(dataStore: DataStore<AppPreferences>) {

        val currentDate = getCurrentDate()

        dataStore.updateData {
            if (it.date != null) {
                Log.d("Update NE", it.date)
                AppPreferences(date = it.date)
            } else {
                Log.d("Update ANO", it.date ?: "")
                it.copy(
                    date = currentDate
                )
            }
        }
    }

    fun getDateSum(): Int {
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        Log.d("DATE SUM", "is ${year + month + day}")
        return year + month + day
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    return Calendar.getInstance().time.run {
        SimpleDateFormat("dd/MM/yyyy").format(this)
    }
}
package com.example.wordlecompose

import android.annotation.SuppressLint
import android.content.Context
import com.example.wordlecompose.util.dataStore
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.AppPreferences
import com.example.AppPreferencesSerializer
import com.example.wordlecompose.MainViewModel
import com.example.wordlecompose.ui.theme.WordleComposeTheme
import com.example.wordlecompose.util.DateHandler
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appPreferences = dataStore.data.collectAsState(initial = AppPreferences()).value

            WordleComposeTheme {

                LaunchedEffect(key1 = true) {
                    DateHandler.updateDate(dataStore = dataStore)
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if(viewModel.isLoading.value)
                    {
                        CircularProgressIndicator()
                    }
                    Text(
                        text = viewModel.wordState.value.word ?: ""
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = appPreferences.date ?: "")
                }
            }
        }
    }
}

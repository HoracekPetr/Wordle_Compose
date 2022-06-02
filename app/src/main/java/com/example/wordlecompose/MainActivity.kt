package com.example.wordlecompose

import android.os.Bundle
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
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.ui.theme.WordleComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val preferences = viewModel.preferences.collectAsState(initial = AppPreferences()).value

            WordleComposeTheme {
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
                    Text(text = preferences.date ?: "")
                }
            }

        }
    }
}

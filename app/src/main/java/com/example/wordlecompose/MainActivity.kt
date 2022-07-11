package com.example.wordlecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wordlecompose.ui.screens.GameScreen
import com.example.wordlecompose.ui.theme.WordleComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleComposeTheme {
                GameScreen(activity = this)
            }
        }
    }
}

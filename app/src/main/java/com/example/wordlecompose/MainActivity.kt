package com.example.wordlecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.ui.components.DoubleSide
import com.example.wordlecompose.ui.components.FlipType
import com.example.wordlecompose.ui.components.LetterBox
import com.example.wordlecompose.ui.screens.GameScreen
import com.example.wordlecompose.ui.theme.WordleComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val currentDate =
                viewModel.preferences.collectAsState(initial = AppPreferences()).value.date
            val currentWord = viewModel.wordState.value.word ?: ""
            val enabled = viewModel.isFlipEnabled.value

            WordleComposeTheme {
                GameScreen(
                    word = currentWord,
                    enabled = enabled,
                    onButtonClick = { viewModel.onButtonClick() }
                )
            }
        }
    }
}

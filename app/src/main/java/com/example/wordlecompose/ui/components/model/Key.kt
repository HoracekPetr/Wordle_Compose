package com.example.wordlecompose.ui.components.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color


data class Key(
    val letter: Char,
    var bgColor: MutableState<Color>
)

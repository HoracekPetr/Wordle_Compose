package com.example.wordlecompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.wordlecompose.ui.screens.states.BGColorState

@Composable
fun LetterRow(
    inputWord: String,
    enabled: Boolean,
    bgColorState: BGColorState
) {

    val delay = 200

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RotatingDoubleSide(
            delay = delay,
            letter = if (inputWord.isEmpty()) "" else inputWord[0].toString().uppercase(),
            flipEnabled = enabled,
            backgroundColor = bgColorState.bgColor1
        )

        RotatingDoubleSide(
            delay = delay * 2,
            letter = if (inputWord.length <= 1) "" else inputWord[1].toString().uppercase(),
            flipEnabled = enabled,
            backgroundColor = bgColorState.bgColor2
        )

        RotatingDoubleSide(
            delay = delay * 3,
            letter = if (inputWord.length <= 2) "" else inputWord[2].toString().uppercase(),
            flipEnabled = enabled,
            backgroundColor = bgColorState.bgColor3
        )

        RotatingDoubleSide(
            delay = delay * 4,
            letter = if (inputWord.length <= 3) "" else inputWord[3].toString().uppercase(),
            flipEnabled = enabled,
            backgroundColor = bgColorState.bgColor4
        )

        RotatingDoubleSide(
            delay = delay * 5,
            letter = if (inputWord.length <= 4) "" else inputWord[4].toString().uppercase(),
            flipEnabled = enabled,
            backgroundColor = bgColorState.bgColor5
        )
    }
}
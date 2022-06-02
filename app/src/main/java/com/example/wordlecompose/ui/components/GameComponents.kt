package com.example.wordlecompose.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LetterRow(word: String, enabled: Boolean) {

    val transition = updateTransition(targetState = enabled, label = "")
    val delay = 500

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(word.length) { count ->
            val rotationX by transition.animateFloat(
                transitionSpec = {
                    tween(
                        delayMillis = count * delay,
                        durationMillis = 500
                    )
                },
                label = ""
            ) {
                if (it) 0f else 180f
            }

            DoubleSide(
                rotationX = rotationX,
                cameraDistance = 100f,
                flipType = FlipType.HORIZONTAL,
                front = { LetterBox(text = word[count].toString()) },
                back = {
                    LetterBox(
                        text = word[count].toString(),
                        backgroundColor = Color(104, 159, 56, 255),
                        textColor = Color.White
                    )
                })
        }
    }
}
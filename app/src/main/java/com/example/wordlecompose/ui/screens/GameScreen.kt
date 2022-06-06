package com.example.wordlecompose.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wordlecompose.ui.components.LetterRow

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
) {

    val todayWord = viewModel.wordState.value.word

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            Column(Modifier.matchParentSize()) {
                LetterRow(
                    inputWord = viewModel.textInputState.value,
                    enabled = viewModel.isFlipEnabled.value,
                    bgColorState = viewModel.letterBgColors.value
                )

                Spacer(modifier = Modifier.size(32.dp))

                OutlinedTextField(
                    label = { Text(text = "Word") },
                    value = viewModel.textInputState.value.uppercase(),
                    onValueChange = { if (it.length <= 5) viewModel.setInputState(it) })

                Spacer(modifier = Modifier.size(16.dp))

                Button(onClick = { viewModel.onButtonClick() }) {
                    Text(text = "Flip")
                    Spacer(modifier = Modifier.size(12.dp))
                    Canvas(modifier = Modifier.size(30.dp)){
                        drawCircle(color = viewModel.letterBgColors.value.bgColor1)
                    }
                }
            }
        }
    }
}
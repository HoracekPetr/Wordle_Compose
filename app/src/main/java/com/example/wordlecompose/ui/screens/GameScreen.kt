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
import com.example.wordlecompose.ui.components.LetterRowColumn

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
) {

    val inputStates = viewModel.inputStates.value
    val flipStates = viewModel.flipStates.value
    val rowBackgroundStates = viewModel.rowBackgroundStates.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            Column(Modifier.matchParentSize()) {

                LetterRowColumn(inputStates = inputStates, flipStates = flipStates, rowBackgroundStates = rowBackgroundStates)

                Spacer(modifier = Modifier.size(32.dp))

                OutlinedTextField(
                    label = { Text(text = "Word") },
                    value = viewModel.textInputState.value.uppercase(),
                    onValueChange = { if (it.length <= 5) viewModel.onEvent(GameScreenEvent.EnteredWord(it)) })

                Spacer(modifier = Modifier.size(16.dp))

                Button(onClick = { viewModel.onEvent(GameScreenEvent.ConfirmButtonClicked) }) {
                    Text(text = "Flip")
                }
            }
        }
    }
}
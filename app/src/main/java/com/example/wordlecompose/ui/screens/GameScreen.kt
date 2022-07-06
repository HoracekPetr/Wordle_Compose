package com.example.wordlecompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wordlecompose.ui.components.Keyboard
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

                LetterRowColumn(
                    inputStates = inputStates,
                    flipStates = flipStates,
                    rowBackgroundStates = rowBackgroundStates
                )

                Spacer(modifier = Modifier.size(18.dp))

                Keyboard(
                    modifier = Modifier.align(alignment = CenterHorizontally),
                    //keyboardLetters = ('A'..'Z').toList(),
                    keyboardKeys = viewModel.keyboardKeys,
                    onKeyboardClick = {
                        if (viewModel.textInputState.value.length <= 5) {
                            viewModel.onEvent(GameScreenEvent.EnteredWord(input = it))
                        }
                    },
                    onBackspaceClick = {
                        viewModel.onEvent(GameScreenEvent.BackspaceWord)
                    },
                    onConfirmClick = {
                        viewModel.onEvent(GameScreenEvent.ConfirmButtonClicked)
                    }
                )
            }
        }
    }
}
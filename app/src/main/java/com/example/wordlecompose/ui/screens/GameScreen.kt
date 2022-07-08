package com.example.wordlecompose.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wordlecompose.R
import com.example.wordlecompose.ui.components.GameEndDialog
import com.example.wordlecompose.ui.components.Keyboard
import com.example.wordlecompose.ui.components.LetterRowColumn

@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
) {

    val inputStates = viewModel.inputStates.value
    val flipStates = viewModel.flipStates.value
    val rowBackgroundStates = viewModel.rowBackgroundStates.value
    val rowErrorStates = viewModel.rowErrorStates.value
    val gameResult = viewModel.gameResult.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            Column(Modifier.matchParentSize()) {

                AnimatedVisibility(
                    visible = viewModel.isGameEnd.value,
                ) {
                    GameEndDialog(
                        gameResult = gameResult,
                        todayWord = viewModel.wordState.value.word ?: "ERROR",
                        onDismissRequest = { viewModel.onEvent(GameScreenEvent.ClosedWinDialog) },
                        onCloseButtonClick = {viewModel.onEvent(GameScreenEvent.ClosedWinDialog)}
                    )
                }

                LetterRowColumn(
                    inputStates = inputStates,
                    flipStates = flipStates,
                    rowBackgroundStates = rowBackgroundStates,
                    rowErrorStates = rowErrorStates
                )

                Spacer(modifier = Modifier.size(18.dp))

                Keyboard(
                    modifier = Modifier.align(alignment = CenterHorizontally),
                    keyboardKeys = viewModel.keyboardKeys,
                    onKeyboardClick = {
                        if (viewModel.textInputState.value.length <= 4) {
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
package com.example.wordlecompose.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wordlecompose.R
import com.example.wordlecompose.ui.components.Keyboard
import com.example.wordlecompose.ui.components.LetterRowColumn
import kotlinx.coroutines.delay

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

                AnimatedVisibility(
                    visible = viewModel.isGameWon.value,

                ) {
                    Dialog(onDismissRequest = { viewModel.onEvent(GameScreenEvent.ClosedWinDialog) }) {
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            color = Color.White
                        ) {
                            Column(
                                modifier = Modifier.padding(all = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = CenterHorizontally
                            ) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    tint = Color(104, 159, 56, 255),
                                    imageVector = Icons.Default.Celebration,
                                    contentDescription = "Icon"
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = stringResource(R.string.congrats),
                                    textAlign = TextAlign.Center,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = stringResource(R.string.todayword) + viewModel.wordState.value.word,
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp
                                )

                                Spacer(modifier = Modifier.size(12.dp))
                                Button(
                                    onClick = { viewModel.onEvent(GameScreenEvent.ClosedWinDialog) },
                                    shape = MaterialTheme.shapes.medium,
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(104, 159, 56, 255))
                                ) {
                                    Text(text = stringResource(R.string.awesome), color = Color.White)
                                }
                            }
                        }
                    }
                }

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
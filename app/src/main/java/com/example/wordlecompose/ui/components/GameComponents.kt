package com.example.wordlecompose.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wordlecompose.R
import com.example.wordlecompose.ui.components.model.Key
import com.example.wordlecompose.ui.theme.MediumGray
import com.example.wordlecompose.util.BoxType
import com.example.wordlecompose.util.GameResult
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    keyboardKeys: List<Key>,
    onKeyboardClick: (String) -> Unit,
    onBackspaceClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        mainAxisAlignment = FlowMainAxisAlignment.Center,
        mainAxisSpacing = 2.dp,
        crossAxisSpacing = 2.dp
    ) {
        keyboardKeys.forEach { key ->
            LetterIconBox(
                boxType = BoxType.KEYBOARD,
                boxSize = 45.dp,
                textSize = 20.sp,
                textColor = MediumGray,
                borderColor = MediumGray,
                text = key.letter.toString(),
                backgroundColor = key.bgColor.value,
                onKeyboardClick = onKeyboardClick
            )
        }

        LetterIconBox(
            boxType = BoxType.KEYBOARD,
            boxSize = 45.dp,
            textColor = MediumGray,
            borderColor = MediumGray,
            icon = Icons.Default.Backspace,
            onBoxClick = onBackspaceClick
        )
        LetterIconBox(
            boxType = BoxType.KEYBOARD,
            boxSize = 45.dp,
            textColor = MediumGray,
            borderColor = MediumGray,
            icon = Icons.Default.Check,
            onBoxClick = onConfirmClick
        )
    }
}

@Composable
fun GameEndDialog(
    gameResult: GameResult,
    todayWord: String,
    onDismissRequest: () -> Unit,
    onCloseButtonClick: () -> Unit,
    onCloseAndEndGameClick: () -> Unit
) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    tint = when (gameResult) {
                        GameResult.WIN -> {
                            Color(104, 159, 56, 255)
                        }
                        GameResult.DEFEAT -> {
                            Color(211, 47, 47, 255)
                        }
                        else -> {
                            Color(0, 0, 0, 255)
                        }
                    },
                    imageVector = when (gameResult) {
                        GameResult.WIN -> {
                            Icons.Default.Celebration
                        }
                        GameResult.DEFEAT -> {
                            Icons.Default.SentimentDissatisfied
                        }
                        else -> {
                            Icons.Default.SentimentSatisfied
                        }
                    },
                    contentDescription = "Icon"
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = when (gameResult) {
                        GameResult.WIN -> {
                            stringResource(R.string.congrats)
                        }
                        GameResult.DEFEAT -> {
                            stringResource(id = R.string.toobad)
                        }
                        else -> {
                            stringResource(id = R.string.guessed)
                        }
                    },
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.todayword) + todayWord,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.size(12.dp))
                Button(
                    onClick = {
                        if (gameResult == GameResult.GUESSED) {
                            onCloseAndEndGameClick()
                        } else {
                            onCloseButtonClick()
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = when (gameResult) {
                            GameResult.WIN -> {
                                Color(104, 159, 56, 255)
                            }
                            GameResult.DEFEAT -> {
                                Color(211, 47, 47, 255)
                            }
                            else -> {
                                Color(0, 0, 0, 255)
                            }
                        }
                    )
                ) {
                    Text(
                        text = when (gameResult) {
                            GameResult.WIN -> {
                                stringResource(id = R.string.awesome)
                            }
                            GameResult.DEFEAT -> {
                                stringResource(id = R.string.okay)
                            }
                            else -> {
                                stringResource(id = R.string.undestood)
                            }
                        }, color = Color.White
                    )
                }
            }
        }
    }
}
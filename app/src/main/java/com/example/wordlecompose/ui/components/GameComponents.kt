package com.example.wordlecompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordlecompose.ui.components.model.Key
import com.example.wordlecompose.ui.theme.MediumGray
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    //keyboardLetters: List<Char>,
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
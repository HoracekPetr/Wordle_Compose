package com.example.wordlecompose.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wordlecompose.util.BoxType

@Preview
@Composable
fun LetterBoxPreview(){
    LetterIconBox(boxType = BoxType.KEYBOARD, text = "B")
}
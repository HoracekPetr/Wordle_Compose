package com.example.wordlecompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wordlecompose.ui.states.*

@Composable
fun LetterRow(
    inputWord: String,
    flipEnabled: Boolean,
    bgColorState: BGColorState,
    isError: Boolean
) {

    val delay = 200

    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RotatingDoubleSide(
            delay = delay,
            letter = if (inputWord.isEmpty()) "" else inputWord[0].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor1,
            isError = isError
        )

        RotatingDoubleSide(
            delay = delay * 2,
            letter = if (inputWord.length <= 1) "" else inputWord[1].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor2,
            isError = isError
        )

        RotatingDoubleSide(
            delay = delay * 3,
            letter = if (inputWord.length <= 2) "" else inputWord[2].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor3,
            isError = isError
        )

        RotatingDoubleSide(
            delay = delay * 4,
            letter = if (inputWord.length <= 3) "" else inputWord[3].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor4,
            isError = isError
        )

        RotatingDoubleSide(
            delay = delay * 5,
            letter = if (inputWord.length <= 4) "" else inputWord[4].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor5,
            isError = isError
        )
    }
}

@Composable
fun LetterRowColumn(
    modifier: Modifier = Modifier,
    inputStates: InputStates,
    flipStates: FlipStates,
    rowBackgroundStates: RowBackgroundStates,
    rowErrorStates: RowErrorStates
) {
   Column(modifier = modifier.fillMaxWidth()) {
       LetterRow(inputWord = inputStates.input1State, flipEnabled = flipStates.row1FlipState.value, bgColorState = rowBackgroundStates.row1BGState.value, isError = rowErrorStates.row1Error.value)
       LetterRow(inputWord = inputStates.input2State, flipEnabled = flipStates.row2FlipState.value, bgColorState = rowBackgroundStates.row2BGState.value, isError = rowErrorStates.row2Error.value)
       LetterRow(inputWord = inputStates.input3State, flipEnabled = flipStates.row3FlipState.value, bgColorState = rowBackgroundStates.row3BGState.value, isError = rowErrorStates.row3Error.value)
       LetterRow(inputWord = inputStates.input4State, flipEnabled = flipStates.row4FlipState.value, bgColorState = rowBackgroundStates.row4BGState.value, isError = rowErrorStates.row4Error.value)
       LetterRow(inputWord = inputStates.input5State, flipEnabled = flipStates.row5FlipState.value, bgColorState = rowBackgroundStates.row5BGState.value, isError = rowErrorStates.row5Error.value)
       LetterRow(inputWord = inputStates.input6State, flipEnabled = flipStates.row6FlipState.value, bgColorState = rowBackgroundStates.row6BGState.value, isError = rowErrorStates.row6Error.value)
   }
}
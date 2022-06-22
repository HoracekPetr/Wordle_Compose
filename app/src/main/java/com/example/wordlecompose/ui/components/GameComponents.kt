package com.example.wordlecompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wordlecompose.ui.screens.states.BGColorState
import com.example.wordlecompose.ui.screens.states.FlipStates
import com.example.wordlecompose.ui.screens.states.InputStates
import com.example.wordlecompose.ui.screens.states.RowBackgroundStates

@Composable
fun LetterRow(
    inputWord: String,
    flipEnabled: Boolean,
    bgColorState: BGColorState
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
            backgroundColor = bgColorState.bgColor1
        )

        RotatingDoubleSide(
            delay = delay * 2,
            letter = if (inputWord.length <= 1) "" else inputWord[1].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor2
        )

        RotatingDoubleSide(
            delay = delay * 3,
            letter = if (inputWord.length <= 2) "" else inputWord[2].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor3
        )

        RotatingDoubleSide(
            delay = delay * 4,
            letter = if (inputWord.length <= 3) "" else inputWord[3].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor4
        )

        RotatingDoubleSide(
            delay = delay * 5,
            letter = if (inputWord.length <= 4) "" else inputWord[4].toString(),
            flipEnabled = flipEnabled,
            backgroundColor = bgColorState.bgColor5
        )
    }
}

@Composable
fun LetterRowColumn(
    modifier: Modifier = Modifier,
    inputStates: InputStates,
    flipStates: FlipStates,
    rowBackgroundStates: RowBackgroundStates
) {
   Column(modifier = modifier.fillMaxWidth()) {
       LetterRow(inputWord = inputStates.input1State, flipEnabled = flipStates.row1FlipState, bgColorState = rowBackgroundStates.row1BGState.value)
       LetterRow(inputWord = inputStates.input2State, flipEnabled = flipStates.row2FlipState, bgColorState = rowBackgroundStates.row2BGState.value)
       LetterRow(inputWord = inputStates.input3State, flipEnabled = flipStates.row3FlipState, bgColorState = rowBackgroundStates.row3BGState.value)
       LetterRow(inputWord = inputStates.input4State, flipEnabled = flipStates.row4FlipState, bgColorState = rowBackgroundStates.row4BGState.value)
       LetterRow(inputWord = inputStates.input5State, flipEnabled = flipStates.row5FlipState, bgColorState = rowBackgroundStates.row5BGState.value)
       LetterRow(inputWord = inputStates.input6State, flipEnabled = flipStates.row6FlipState, bgColorState = rowBackgroundStates.row6BGState.value)
   }
}
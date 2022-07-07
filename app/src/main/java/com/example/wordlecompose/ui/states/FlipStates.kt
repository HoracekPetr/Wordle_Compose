package com.example.wordlecompose.ui.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FlipStates(
    val row1FlipState: MutableState<Boolean> = mutableStateOf(false),
    val row2FlipState: MutableState<Boolean> = mutableStateOf(false),
    val row3FlipState: MutableState<Boolean> = mutableStateOf(false),
    val row4FlipState: MutableState<Boolean> = mutableStateOf(false),
    val row5FlipState: MutableState<Boolean> = mutableStateOf(false),
    val row6FlipState: MutableState<Boolean> = mutableStateOf(false),
)

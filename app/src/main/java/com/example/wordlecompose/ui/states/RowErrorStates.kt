package com.example.wordlecompose.ui.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class RowErrorStates(
    val row1Error: MutableState<Boolean> = mutableStateOf(false),
    val row2Error: MutableState<Boolean> = mutableStateOf(false),
    val row3Error: MutableState<Boolean> = mutableStateOf(false),
    val row4Error: MutableState<Boolean> = mutableStateOf(false),
    val row5Error: MutableState<Boolean> = mutableStateOf(false),
    val row6Error: MutableState<Boolean> = mutableStateOf(false),
)

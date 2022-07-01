package com.example.wordlecompose.ui.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class RowBackgroundStates(
    val row1BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
    val row2BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
    val row3BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
    val row4BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
    val row5BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
    val row6BGState: MutableState<BGColorState> = mutableStateOf(BGColorState()),
)

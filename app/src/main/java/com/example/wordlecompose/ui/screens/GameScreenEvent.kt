package com.example.wordlecompose.ui.screens

sealed class GameScreenEvent{
    data class EnteredWord(val input: String): GameScreenEvent()
    object ConfirmButtonClicked: GameScreenEvent()
}

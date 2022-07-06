package com.example.wordlecompose.ui.screens

sealed class GameScreenEvent{
    data class EnteredWord(val input: String): GameScreenEvent()
    object BackspaceWord: GameScreenEvent()
    object ConfirmButtonClicked: GameScreenEvent()
    object ClosedWinDialog: GameScreenEvent()
}

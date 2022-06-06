package com.example.wordlecompose.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toUpperCase
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordlecompose.data.database.Word
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.ui.screens.states.BGColorState
import com.example.wordlecompose.util.DateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val repository: WordRepository,
    private val dataStore: DataStore<AppPreferences>
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _wordState = mutableStateOf(Word())
    val wordState: State<Word> = _wordState

    private val _isFlipEnabled = mutableStateOf(false)
    val isFlipEnabled: State<Boolean> = _isFlipEnabled

    val preferences: Flow<AppPreferences> = dataStore.data

    private val _textInputState = mutableStateOf("")
    val textInputState: State<String> = _textInputState

    private val _letterBgColors = mutableStateOf(BGColorState())
    val letterBgColors: State<BGColorState> = _letterBgColors

    init {
        viewModelScope.launch {
            getWordForToday()
            updateDate()
        }
    }


    private suspend fun updateDate() {

        val currentDate = DateHandler.getCurrentDate()

        dataStore.updateData {
            if (it.date != null) {
                AppPreferences(date = it.date)
            } else {
                it.copy(
                    date = currentDate
                )
            }
        }
    }

    private suspend fun getWordForToday() {
        _isLoading.value = true

        val rowCount = repository.getRowCount()
        _wordState.value = _wordState.value.copy(
            word = repository.loadOneWord(
                id = Random(seed = DateHandler.getDateSum()).nextInt(
                    from = 0,
                    until = rowCount
                )
            ).word?.uppercase()
        )

        _isLoading.value = false
    }

    fun onButtonClick() {
        if (_textInputState.value.length == _wordState.value.word?.length) {
            setBgColors()
            println(_letterBgColors.value)
            _isFlipEnabled.value = !_isFlipEnabled.value
        }
    }

    fun setInputState(value: String) {
        _textInputState.value = value
    }

    private fun setBgColors() {
        _letterBgColors.value = _letterBgColors.value.copy(
            bgColor1 = setColor(
                inputLetter = _textInputState.value[0],
                wordLetter = _wordState.value.word?.get(0) ?: Char(0),
                word = _wordState.value.word ?: ""
            ),
            bgColor2 = setColor(
                inputLetter = _textInputState.value[1],
                wordLetter = _wordState.value.word?.get(1) ?: Char(0),
                word = _wordState.value.word ?: ""
            ),
            bgColor3 = setColor(
                inputLetter = _textInputState.value[2],
                wordLetter = _wordState.value.word?.get(2) ?: Char(0),
                word = _wordState.value.word ?: ""
            ),
            bgColor4 = setColor(
                inputLetter = _textInputState.value[3],
                wordLetter = _wordState.value.word?.get(3) ?: Char(0),
                word = _wordState.value.word ?: ""
            ),
            bgColor5 = setColor(
                inputLetter = _textInputState.value[4],
                wordLetter = _wordState.value.word?.get(4) ?: Char(0),
                word = _wordState.value.word ?: ""
            )
        )
    }


    private fun setColor(inputLetter: Char, wordLetter: Char, word: String): Color {
        println("INPUT: $inputLetter, WORDLETTER: $wordLetter")
        return when {
            inputLetter == wordLetter -> Color(104, 159, 56, 255)
            word.contains(inputLetter) -> Color(251, 192, 45, 255)
            else -> Color(194, 194, 194, 255)
        }
    }
}
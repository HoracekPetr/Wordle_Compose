package com.example.wordlecompose.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordlecompose.data.database.Word
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.ui.components.model.Key
import com.example.wordlecompose.ui.states.BGColorState
import com.example.wordlecompose.ui.states.FlipStates
import com.example.wordlecompose.ui.states.InputStates
import com.example.wordlecompose.ui.states.RowBackgroundStates
import com.example.wordlecompose.util.DateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _textInputState = mutableStateOf("")
    val textInputState: State<String> = _textInputState

    private val _flipStates = mutableStateOf(FlipStates())
    val flipStates = _flipStates

    private val _rowBackgroundStates = mutableStateOf(RowBackgroundStates())
    val rowBackgroundStates = _rowBackgroundStates

    private val _inputStates = mutableStateOf(InputStates())
    val inputStates = _inputStates

    private val _currentRow = mutableStateOf(1)
    val currentRow: State<Int> = _currentRow

    private val _keyboardKeys = mutableStateListOf<Key>()
    val keyboardKeys = _keyboardKeys

    private val _isGameWon = mutableStateOf(false)
    val isGameWon = _isGameWon

    val preferences: Flow<AppPreferences> = dataStore.data

    init {
        viewModelScope.launch {
            getWordForToday()
            updateDate()
        }
        ('A'..'Z').forEach { letter ->
            _keyboardKeys.add(Key(letter = letter, bgColor = mutableStateOf(Color.Transparent)))
        }
    }

    fun onEvent(event: GameScreenEvent) {
        when (event) {
            is GameScreenEvent.ConfirmButtonClicked -> {
                viewModelScope.launch {
                    if (_textInputState.value.length == _wordState.value.word?.length) {
                        when (_currentRow.value) {
                            1 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row1BGState,
                                    flipState = _flipStates.value.row1FlipState
                                )
                            }
                            2 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row2BGState,
                                    flipState = _flipStates.value.row2FlipState
                                )
                            }
                            3 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row3BGState,
                                    flipState = _flipStates.value.row3FlipState
                                )
                            }
                            4 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row4BGState,
                                    flipState = _flipStates.value.row4FlipState
                                )
                            }
                            5 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row5BGState,
                                    flipState = _flipStates.value.row5FlipState
                                )
                            }
                            6 -> {
                                onConfirmButtonClick(
                                    bgColorState = _rowBackgroundStates.value.row6BGState,
                                    flipState = _flipStates.value.row6FlipState
                                )
                            }
                            else -> {

                            }
                        }
                    }
                }
            }
            is GameScreenEvent.EnteredWord -> {
                when (_currentRow.value) {
                    1 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input1State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }

                    2 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input2State = _textInputState.value
                        )
                    }

                    3 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input3State = _textInputState.value
                        )
                    }

                    4 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input4State = _textInputState.value.uppercase()
                        )
                    }

                    5 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input5State = _textInputState.value
                        )
                    }

                    6 -> {
                        _textInputState.value += event.input.uppercase()
                        _inputStates.value = _inputStates.value.copy(
                            input6State = _textInputState.value
                        )
                    }

                    else -> {

                    }
                }
            }
            is GameScreenEvent.BackspaceWord -> {
                when (currentRow.value) {
                    1 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input1State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                    2 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input2State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                    3 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input3State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                    4 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input4State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                    5 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input5State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                    6 -> {
                        _textInputState.value = _textInputState.value.dropLast(1)
                        _inputStates.value = _inputStates.value.copy(
                            input6State = _textInputState.value
                        )
                        println(_textInputState.value)
                    }
                }
            }
            GameScreenEvent.ClosedWinDialog -> {
                _isGameWon.value = false
            }
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

        println(_wordState.value.word)

        _isLoading.value = false
    }


    private fun setBgColors(rowBGColorState: MutableState<BGColorState>) {
        rowBGColorState.value = rowBGColorState.value.copy(
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
        println("INPUT LETTER: $inputLetter")
        println("WORD LETTER: $wordLetter")
        return when {
            inputLetter == wordLetter -> Color(104, 159, 56, 255)
            word.contains(inputLetter) -> Color(251, 192, 45, 255)
            else -> Color(194, 194, 194, 255)
        }
    }

    private fun setKeyboardLetterColor() {
        for (i in 0 until _textInputState.value.length) {
            val currentKey = _keyboardKeys.find { it.letter == _textInputState.value[i] }

            currentKey?.let {
                when {
                    _textInputState.value[i] == _wordState.value.word?.get(i) ?: "" -> {
                        it.bgColor.value = Color(104, 159, 56, 255)
                    }

                    _wordState.value.word?.contains(_textInputState.value[i]) ?: false -> {
                        it.bgColor.value = Color(251, 192, 45, 255)
                    }

                    else -> {
                        it.bgColor.value = Color(194, 194, 194, 255)
                    }
                }
            }
        }
    }

    private fun checkWinConditions() {
        if (_wordState.value.word != _textInputState.value) {
            _isGameWon.value = false
            return
        }
        _isGameWon.value = true
        println(_isGameWon.value)
    }

    private suspend fun checkIfWordExists(inputWord: String): Boolean {
        return repository.checkIfWordExists(inputWord = inputWord)
    }

    private suspend fun onConfirmButtonClick(
        bgColorState: MutableState<BGColorState>,
        flipState: MutableState<Boolean>
    ) {
        println("Start")
        val wordCheckValue = checkIfWordExists(inputWord = _textInputState.value.lowercase())
        println("Word check: $wordCheckValue")
        if (!wordCheckValue) {
            return
        }
        setBgColors(rowBGColorState = bgColorState)
        flipState.value = true
        setKeyboardLetterColor()
        delay(1200)
        checkWinConditions()
        _textInputState.value = ""
        _currentRow.value++
    }
}
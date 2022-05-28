package com.example.wordlecompose

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.AppPreferences
import com.example.wordlecompose.data.database.Word
import com.example.wordlecompose.data.database.WordDAO
import com.example.wordlecompose.data.database.WordEntity
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.util.DateHandler
import com.example.wordlecompose.util.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WordRepository
): ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _wordState = mutableStateOf(Word())
    val wordState: State<Word> = _wordState

    init {
        viewModelScope.launch() {

            _isLoading.value = true

            val rowCount = repository.getRowCount()
            _wordState.value = _wordState.value.copy(
                word = repository.loadOneWord(id = Random(seed = DateHandler.getDateSum()).nextInt(from = 0, until = rowCount)).word
            )

            _isLoading.value = false
        }
    }

}
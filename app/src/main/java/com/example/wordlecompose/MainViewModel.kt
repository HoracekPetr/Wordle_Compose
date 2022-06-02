package com.example.wordlecompose

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.data.database.Word
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.util.DateHandler
import com.example.wordlecompose.util.DateHandler.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WordRepository,
    private val dataStore: DataStore<AppPreferences>
): ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _wordState = mutableStateOf(Word())
    val wordState: State<Word> = _wordState

    val preferences: Flow<AppPreferences> = dataStore.data

    init {
        viewModelScope.launch {
            getWordForToday()
            updateDate()
        }
    }

    private suspend fun updateDate() {

        val currentDate = getCurrentDate()

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

    private suspend fun getWordForToday(){
        _isLoading.value = true

        val rowCount = repository.getRowCount()
        _wordState.value = _wordState.value.copy(
            word = repository.loadOneWord(id = Random(seed = DateHandler.getDateSum()).nextInt(from = 0, until = rowCount)).word
        )

        _isLoading.value = false
    }

}
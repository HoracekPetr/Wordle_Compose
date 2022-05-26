package com.example.wordlecompose.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordlecompose.data.database.WordDAO
import com.example.wordlecompose.data.database.WordEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: WordDAO
): ViewModel() {

    private val _wordState = mutableStateOf(WordEntity())
    val wordState: State<WordEntity> = _wordState

    init {
        viewModelScope.launch {
            dao.loadOneWord(5).collect{ entity ->
                Log.d("Collecting...", entity.word ?: "")
                _wordState.value = entity
            }
        }
    }

}
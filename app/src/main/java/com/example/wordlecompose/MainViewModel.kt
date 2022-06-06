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

): ViewModel() {

}
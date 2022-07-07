package com.example.wordlecompose.data.repository

import com.example.wordlecompose.data.database.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    suspend fun loadOneWord(id: Int): Word

    suspend fun getRowCount(): Int

    suspend fun checkIfWordExists(inputWord: String): Boolean
}
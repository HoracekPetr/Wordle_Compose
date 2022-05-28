package com.example.wordlecompose.data.repository

import com.example.wordlecompose.data.database.Word
import com.example.wordlecompose.data.database.WordDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordRepositoryImpl(
    private val dao: WordDAO
): WordRepository {
    override suspend fun loadOneWord(id: Int): Word {
        return dao.loadOneWord(wordId = id).toWord()
    }

    override suspend fun getRowCount(): Int {
        return dao.getRowCount()
    }
}
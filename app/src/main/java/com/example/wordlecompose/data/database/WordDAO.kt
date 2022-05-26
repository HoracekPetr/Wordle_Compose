package com.example.wordlecompose.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDAO {

    @Query("SELECT * from words WHERE id = (:wordId)")
    fun loadOneWord(wordId: Int): Flow<WordEntity>

}
package com.example.wordlecompose.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDAO {

    @Query("SELECT COUNT(*) FROM words")
    suspend fun getRowCount(): Int

    @Query("SELECT * from words WHERE id = (:wordId)")
    suspend fun loadOneWord(wordId: Int): WordEntity

}
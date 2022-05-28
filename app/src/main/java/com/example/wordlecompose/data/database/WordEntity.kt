package com.example.wordlecompose.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var word: String? = null
){
  fun toWord(): Word{
      return Word(
          word = this.word
      )
  }
}

data class Word(
    val word: String? = null
)


package com.example.wordlecompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun getDao(): WordDAO
}
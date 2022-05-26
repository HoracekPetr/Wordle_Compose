package com.example.wordlecompose.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wordlecompose.data.database.WordDAO
import com.example.wordlecompose.data.database.WordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDictionaryDatabase(@ApplicationContext appContext: Context): WordDatabase {
        return Room.databaseBuilder(
            appContext,
            WordDatabase::class.java,
            "WordsDb"
        ).createFromAsset("database/WordsDb.db").build()
    }

    @Singleton
    @Provides
    fun provideWordDao(database: WordDatabase): WordDAO {
        return database.getDao()
    }

}
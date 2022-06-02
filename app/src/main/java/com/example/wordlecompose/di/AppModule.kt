package com.example.wordlecompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.di.preferences.AppPreferencesSerializer
import com.example.wordlecompose.data.database.WordDAO
import com.example.wordlecompose.data.database.WordDatabase
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.data.repository.WordRepositoryImpl
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

    @Singleton
    @Provides
    fun provideWordRepository(dao: WordDAO): WordRepository{
        return WordRepositoryImpl(dao = dao)
    }

    @Singleton
    @Provides
    fun provideAppPreferences(): AppPreferences {
        return AppPreferences()
    }

    @Singleton
    @Provides
    fun provideSerializer(): Serializer<AppPreferences>{
        return AppPreferencesSerializer
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context, serializer: Serializer<AppPreferences>): DataStore<AppPreferences>{
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = {context.dataStoreFile("wordle-preferences.json")}
        )
    }
}
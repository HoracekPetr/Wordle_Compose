package com.example.wordlecompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.room.Room
import com.example.wordlecompose.di.preferences.AppPreferences
import com.example.wordlecompose.di.preferences.AppPreferencesSerializer
import com.example.wordlecompose.data.database.WordDAO
import com.example.wordlecompose.data.database.WordDatabase
import com.example.wordlecompose.data.repository.WordRepository
import com.example.wordlecompose.data.repository.WordRepositoryImpl
import com.example.wordlecompose.di.preferences.WORDLE_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    fun provideDataStore(@ApplicationContext context: Context): DataStore<AppPreferences>{
        return DataStoreFactory.create(
            serializer = AppPreferencesSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {context.dataStoreFile(WORDLE_PREFERENCES)}
        )
    }
}
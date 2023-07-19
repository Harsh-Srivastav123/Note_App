package com.example.note_app.di

import android.content.Context
import androidx.room.Room
import com.example.note_app.data.NoteDatabase
import com.example.note_app.data.NoteDatabaseDao
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
    fun provideNotesDao(noteDatabase:NoteDatabase): NoteDatabaseDao {
        return noteDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context):NoteDatabase{
        return   Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_table"
        ).fallbackToDestructiveMigration().build()
    }
}
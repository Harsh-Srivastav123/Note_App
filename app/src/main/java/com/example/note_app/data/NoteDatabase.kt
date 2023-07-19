package com.example.note_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note_app.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}
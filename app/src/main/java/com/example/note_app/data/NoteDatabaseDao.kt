package com.example.note_app.data

import androidx.room.*
import com.example.note_app.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao  {

    @Query("SELECT * from note_table")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from note_table where id=:id")
    suspend fun getNoteById(id:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note:Note)

    @Query("DELETE * from note_table")
    suspend fun deleteAllNote()

    @Delete
    suspend fun deleteNote(note:Note)

}
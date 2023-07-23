package com.example.note_app.data

import androidx.room.*
import com.example.note_app.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao  {

    @Query("SELECT * from note_table")
    fun getNotes(): Flow<List<Note>>

//    @Query("SELECT * from note_table where note_id=:id")
//    suspend fun getNoteById(id:String?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateNote(note:Note)

//    @Query("DELETE * from note_table")
//    suspend fun deleteAllNote()

    @Query("DELETE from note_table WHERE note_id=:note_id")
    suspend fun deleteNote(note_id:String)

}
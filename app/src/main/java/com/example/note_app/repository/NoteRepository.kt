package com.example.note_app.repository

import com.example.note_app.data.NoteDatabaseDao
import com.example.note_app.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note:Note){
        noteDatabaseDao.insertNote(note)
    }
//    suspend fun updateNote(note: String){
//        noteDatabaseDao.updateNote(note);
//    }

    suspend fun deleteNote(note_id:String){
        noteDatabaseDao.deleteNote(note_id)
    }
//    suspend fun deleteAllNotes(){
//        noteDatabaseDao.deleteAllNote()
//    }
    fun getAllNotes ():Flow<List<Note>> {
        return noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
    }


}
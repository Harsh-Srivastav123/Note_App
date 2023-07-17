package com.example.note_app.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.note_app.model.Note

class NoteViewModel:ViewModel() {
    private var noteList = mutableStateListOf<Note>()
    fun addNote(note:Note){
        noteList.add(note)
    }
    fun removeNote(note:Note){
        noteList.remove(note)
    }
    fun getAllNote():List<Note>{
        return noteList
    }

}
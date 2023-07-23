package com.example.note_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app.model.Note
import com.example.note_app.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository) :ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList<Note>())
    val noteList=_noteList.asStateFlow()
    fun size():Int{
        return noteList.value.size
    }



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect(){
                if(it!=null){
                    _noteList.value=it
                }


            }
        }
    }

    fun addNote(note:Note)=viewModelScope.launch {
        repository.addNote(note )
    }

//    fun updateNote(note:Note)=viewModelScope.launch {
//        repository.updateNote(note )
//    }
    fun deleteNote(note_id:String)=viewModelScope.launch {
        repository.deleteNote(note_id)
    }

//    fun deleteNote(note:Note)=viewModelScope.launch {
//        repository.deleteNote(note)
//    }
}
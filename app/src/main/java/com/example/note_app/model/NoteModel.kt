package com.example.note_app.model

import android.net.Uri

data class NoteModel(
    val title:String,
    val note:String,
    val uriList: List<Uri>,
    val timeStamp:String
)
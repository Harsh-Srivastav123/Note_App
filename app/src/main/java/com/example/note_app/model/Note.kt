package com.example.note_app.model

import android.net.Uri
import java.util.UUID

data class Note(
//    val id:UUID=UUID.randomUUID(),
    val title:String,
    val note:String,
    val uriList: List<Uri>,
    val timeStamp:String
)
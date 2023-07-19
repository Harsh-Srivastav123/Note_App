package com.example.note_app.model

import android.net.Uri
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
@Entity(tableName = "Note_table")
data class Note(
    @PrimaryKey
    val id:UUID=UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title:String,
    @ColumnInfo(name="note_description")
    val note:String,
    @ColumnInfo(name = "note_image")
    val uriList: List<Uri>,
    @ColumnInfo(name = "note_time")
    val timeStamp:String
)
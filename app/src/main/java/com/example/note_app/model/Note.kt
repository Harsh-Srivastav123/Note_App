package com.example.note_app.model

import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID
@Parcelize
@Entity(tableName = "Note_table")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    val id:String,
    @ColumnInfo(name = "note_title")
    val title:String,
    @ColumnInfo(name="note_description")
    val note:String,
    @ColumnInfo(name = "note_image")
    val imageList: List<String>?,
    @ColumnInfo(name = "note_time")
    val timeStamp:String
) : Parcelable
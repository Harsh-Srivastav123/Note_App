package com.example.note_app.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class StringListConverter {
    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return stringList.joinToString(",")
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(",")
    }
}
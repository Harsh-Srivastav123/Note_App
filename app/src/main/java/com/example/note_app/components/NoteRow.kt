package com.example.note_app.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.note_app.model.Note
import com.example.note_app.ui.theme.Primary
import java.io.File



@Composable
fun NoteRow(note: Note,context:Context,onNoteClick:(Note)->Unit) {

    if (note != null) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Card(
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth()
                .clickable {
                    // click event handle
                    onNoteClick.invoke(note)

                },
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Primary)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    if (note.imageList!=null) {


                          if(note.imageList.size>=2){
                              if(note.imageList[1] != "") {
                                  Surface(
                                      Modifier
                                          .padding(10.dp)
                                          .size(100.dp), elevation = 4.dp,
                                      shape = CircleShape
                                  ) {
                                      val imgFile = File(note.imageList[1])

                                      // on below line we are checking if the image file exist or not.
                                      var imgBitmap: Bitmap? = null
                                      if (imgFile.exists()) {
                                          imgBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                                      }
                                      Image(
                                          painter = rememberAsyncImagePainter(imgBitmap),
                                          contentDescription = null
                                      )

//                            val bitmap:Bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolverl,uri)
                                  }
                              }
                          }
//

                    }
                    Column(Modifier.padding(4.dp)) {

                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "Time: ${note.timeStamp}",
                            style = MaterialTheme.typography.caption
                        )

                    }
                }

                //row ended

                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Spacer(modifier = Modifier.size(5.dp))
                        Divider()
                        Text(
                            text = "Description: ${note.note}", fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )


                    }
                }

                Row() {
                    Spacer(Modifier.fillMaxSize(0.90f))
                    Icon(imageVector = if (expanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.ArrowDropDown,
                        contentDescription = null,
                        Modifier.clickable {


                            //action on click
                            expanded.value = !expanded.value
                        })
                }
            }

        }
    }

}
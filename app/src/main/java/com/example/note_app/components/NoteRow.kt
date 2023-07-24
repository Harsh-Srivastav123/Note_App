package com.example.note_app.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.note_app.model.Note
import com.example.note_app.ui.theme.Primary
import com.example.note_app.viewModel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File


@Composable

fun NoteRow(
    note: Note,
    context: Context,
    onNoteClick: (Note) -> Unit,
    noteViewModel: NoteViewModel? = null
) {
    val isDialog = remember {
        mutableStateOf(false)
    }

    if (note != null) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Surface {
            Card(
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(onLongPress = {
                            //noteViewModel?.deleteNote(note.id)
                            isDialog.value = true
                        },
                            onTap = {
                                expanded.value = !expanded.value
                            })
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 8.dp
            )
            {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Primary)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (note.imageList != null) {


                            if (note.imageList.size >= 2) {
                                if (note.imageList[1] != "") {
                                    Surface(
                                        Modifier
                                            .padding(10.dp)
                                            .size(80.dp), elevation = 4.dp,
                                        shape = CircleShape
                                    ) {
                                        val imgFile = File(note.imageList[1])

                                        // on below line we are checking if the image file exist or not.
                                        var imgBitmap: Bitmap? = null
                                        if (imgFile.exists()) {
                                            imgBitmap =
                                                BitmapFactory.decodeFile(imgFile.absolutePath)
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
                        Column(Modifier.padding(4.dp)) {
                           val paths= mutableListOf<String>(emptyList<String>().toString())
                           note.imageList!!.forEach {
                               if(it.length>10){
                                   Log.i("path_image", it)
                                   paths.add(it)
                               }
                           }
                            if(paths.size>1){
                                Log.i("here", "NoteRow: "+paths.size.toString())
                                ImageSliderBitmap(imageList = paths)
                            }
                            
                            Spacer(modifier = Modifier.size(5.dp))
                            Divider()
                            Text(
                                text = "Description: ${note.note}", fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )


                        }
                    }

                    Row {
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
            if (isDialog.value) {
                Dialog(onDismissRequest = { isDialog.value = false }) {
                    Card(
                        elevation = 20.dp, modifier = Modifier
                            .padding(4.dp)
                            .height(240.dp)
                            .width(180.dp)
                    ) {
                        Column {
                            Row(
                                Modifier
                                    .height(80.dp)
                                    .padding(start = 2.dp, end = 2.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        onNoteClick.invoke(note)
                                        isDialog.value = false
                                    }) {
                                Text(
                                    text = "Update", style = MaterialTheme.typography.h4,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                                Spacer(modifier = Modifier.fillMaxSize(0.60f))
                                Image(
                                    imageVector = Icons.Rounded.Update, contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                )
                            }
                            Divider()
                            Row(
                                Modifier
                                    .height(80.dp)
                                    .padding(start = 2.dp, end = 2.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        noteViewModel?.deleteNote(note_id = note.id)
                                        isDialog.value = false
                                    }) {
                                Text(
                                    text = "Delete", style = MaterialTheme.typography.h4,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                                Spacer(modifier = Modifier.fillMaxSize(0.60f))
                                Image(
                                    imageVector = Icons.Rounded.Delete, contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                )
                            }
                            Divider()
                            Row(
                                Modifier
                                    .height(80.dp)
                                    .padding(start = 2.dp, end = 2.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        isDialog.value = false
                                    }) {
                                Text(
                                    text = "Lock  ", style = MaterialTheme.typography.h4,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                                Spacer(modifier = Modifier.fillMaxSize(0.60f))
                                Image(
                                    imageVector = Icons.Rounded.Lock, contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}



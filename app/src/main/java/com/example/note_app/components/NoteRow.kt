package com.example.note_app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.note_app.model.Note

@Preview(showBackground = true)
@Composable
fun NoteRow(note: Note? = null) {

    if (note != null) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Card(
            Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    // click event handle
                },
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = 6.dp
        ) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    if (note.uriList.isNotEmpty()) {
                        Surface(
                            Modifier
                                .padding(10.dp)
                                .size(100.dp), elevation = 4.dp,
                            shape = RectangleShape
                        ) {
                            Image(painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(
                                    LocalContext.current
                                ).data(data = note.uriList[0]).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    transformations(CircleCropTransformation())
                                }).build()
                            ), contentDescription = null)
                        }
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
                            text = "Director: ${note.note}", fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )


                    }
                }

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
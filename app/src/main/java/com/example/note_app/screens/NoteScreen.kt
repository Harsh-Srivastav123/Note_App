package com.example.note_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Preview(showBackground = true)
@Composable
fun NoteScreen() {

    var title = remember {
        mutableStateOf("")
    }
    var note = remember {
        mutableStateOf("")
    }
    var formatted = remember {
        mutableStateOf("")
    }
    var timer= remember() {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = timer.value ){
       while (timer.value){
           val current = LocalDateTime.now()
           val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
           formatted.value=current.format(formatter).toString()
           delay(100L)
       }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.size(3.dp))
                Image(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null,
                    Modifier
                        .size(40.dp)
                        .clickable {

                        })
                Spacer(modifier = Modifier.fillMaxWidth(0.85f))
                Image(imageVector = Icons.Rounded.Done, contentDescription = null,
                    Modifier
                        .size(40.dp)
                        .clickable {

                        })
            }
            TextField(
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                Modifier
                    .fillMaxWidth()
                    .size(120.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),

                placeholder = {
                    Text(text = "Title", style = MaterialTheme.typography.h4)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    cursorColor = MaterialTheme.colors.primary,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    disabledTextColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    disabledIndicatorColor = MaterialTheme.colors.background
                ),
            )

            Text(
                text = formatted.value,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 8.dp),
                color = Color.Gray
            )
            Divider()
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = note.value, onValueChange = {
                note.value = it
            }, placeholder = {
                Text(text = "write your note here", fontWeight = FontWeight.Light)
            },

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    cursorColor = MaterialTheme.colors.primary,
                    focusedIndicatorColor = MaterialTheme.colors.primary,
                    disabledTextColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.primary,
                    disabledIndicatorColor = MaterialTheme.colors.background
                )

            )
        }
    }
}
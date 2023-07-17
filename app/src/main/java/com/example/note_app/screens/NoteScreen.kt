package com.example.note_app.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.UploadFile
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.note_app.components.ImageSlider
import com.example.note_app.model.Note
import com.example.note_app.viewModel.NoteViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Preview(showBackground = true)
@Composable
fun NoteScreen() {
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    val noteViewModel:NoteViewModel= viewModel()

    //image from gallery
    val galleryImage= remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents(), onResult = {
        galleryImage.value=it
    })

    val title = remember {
        mutableStateOf("")
    }
    val note = remember {
        mutableStateOf("")
    }
    val formatted = remember {
        mutableStateOf("")
    }
    val timer= remember() {
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

      Scaffold(
          Modifier
              .fillMaxWidth()
              .fillMaxHeight(),
          topBar = {
              Box(
                  Modifier
                      .fillMaxWidth()
                      .padding(top = 20.dp)){
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
                      Spacer(modifier = Modifier.fillMaxWidth(0.68f))
                      Image(imageVector = Icons.Rounded.UploadFile, contentDescription = null,
                          modifier = Modifier.clickable {
                              launcher.launch("image/*")
//                    Log.i("images", galleryImage.value.get(0).toString())

//                    launcher.
                          })

                      Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                      Image(imageVector = Icons.Rounded.Done, contentDescription = null,
                          Modifier
                              .size(40.dp)
                              .clickable {
                                  noteViewModel.addNote(
                                      Note(
                                          title.value,
                                          note.value,
                                          galleryImage.value,
                                          formatted.value.toString()
                                      )
                                  )

                              })
                  }

              }

          }
      ) {

          Column(
              Modifier
                  .fillMaxWidth()
                  .verticalScroll(rememberScrollState())
                  .fillMaxHeight()
                  .padding(it)
          ) {

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
              if(galleryImage.value.isNotEmpty()){
                  ImageSlider(galleryImage.value)
                  Divider()
              }
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
}


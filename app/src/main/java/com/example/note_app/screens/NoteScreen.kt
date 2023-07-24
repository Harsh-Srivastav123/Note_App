package com.example.note_app.screens

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note_app.components.ImageSlider
import com.example.note_app.model.Note
import com.example.note_app.viewModel.NoteViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


@Preview(showBackground = true)
@Composable
fun NoteScreen(
    navController: NavController? = null,
    context: Context? = null,
    noteViewModel: NoteViewModel? = null,
    note_obj: Note? = null
) {



    //image from gallery

    val galleryImage = remember {
        mutableStateListOf<String>(emptyList<String>().toString())
    }
    val listAlert = remember {
        mutableStateOf(false)
    }
    val title = remember {
        mutableStateOf("")
    }
    val note = remember {
        mutableStateOf("")
    }
    var id: String = UUID
        .randomUUID()
        .toString()

    if (note_obj != null) {
        title.value = note_obj.title
        note.value = note_obj.note
        id = note_obj.id

        listAlert.value = true
    }

    val formatted = remember {
        mutableStateOf("")
    }
    val timer = remember {
        mutableStateOf(true)
    }
    var uriImage = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {
            uriImage.value = it
        })


    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = timer.value) {
        while (timer.value) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            formatted.value = current.format(formatter).toString()

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
                        .padding(top = 20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.size(3.dp))
                        Image(imageVector = Icons.Rounded.KeyboardArrowLeft,
                            contentDescription = null,
                            Modifier
                                .size(40.dp)
                                .clickable {
                                    navController?.popBackStack()
                                    navController?.navigate("HomeScreen")

                                })
                        Spacer(modifier = Modifier.fillMaxWidth(0.68f))
                        Image(imageVector = Icons.Rounded.UploadFile, contentDescription = null,
                            modifier = Modifier.clickable {
                                launcher.launch("image/*")

                            })

                        Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                        Image(imageVector = Icons.Rounded.Done, contentDescription = null,
                            Modifier
                                .size(40.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        val bitmapList = mutableListOf<Bitmap>()
//
                                        uriImage.value.forEach {
                                            bitmapList.add(
                                                MediaStore.Images.Media.getBitmap(
                                                    context?.contentResolver,
                                                    it
                                                )
                                            )
                                        }

                                        bitmapList.forEach {
                                            val stream = ByteArrayOutputStream()
                                            it.compress(Bitmap.CompressFormat.PNG, 100, stream)
                                            val imageData: ByteArray = stream.toByteArray()
                                            val fileName =
                                                "image_" + System.currentTimeMillis() + ".png"
                                            val imageFile = File(context!!.filesDir, fileName)
                                            val fos = FileOutputStream(imageFile)
                                            fos.write(imageData)
                                            fos.close()
                                            val path: String = imageFile.absolutePath
                                            galleryImage.add(path)

                                        }
                                        noteViewModel?.addNote(
                                            Note(
                                                id = id,
                                                title = title.value,
                                                note = note.value,
                                                imageList = galleryImage,
                                                timeStamp = formatted.value
                                            )
                                        )

                                        navController?.navigate("HomeScreen")
                                    }
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
                if (uriImage.value.isNotEmpty()) {
                    ImageSlider(uriImage.value)
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
                if (listAlert.value) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.fillMaxSize(0.25f))
                        Text(
                            text = "On Updating , image list is reassign",
                            fontStyle = FontStyle.Italic,
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

//fun DoneClickableEvent(value: String, value1: String, value2: List<Uri>, toString: String) {
//    val noteViewModel= viewModel<NoteViewModel>()
//
//}


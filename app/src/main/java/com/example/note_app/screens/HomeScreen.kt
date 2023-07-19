package com.example.note_app.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.note_app.components.NoteRow
import com.example.note_app.viewModel.NoteViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun HomeScreen(navController: NavController? = null,context: Context?=null ) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars


    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        elevation = 10.dp
    ) {
        Scaffold(topBar = {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(2.dp)) {
                Text(
                    text = "Note",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(top = 50.dp, start = 5.dp, bottom = 5.dp)
                )
            }
        },
            floatingActionButton = {

                Button(
                    onClick = {
                              navController?.navigate("NoteScreen")
                    },
                    Modifier
                        .padding(3.dp)
                        .size(60.dp),
                    shape = CircleShape
                ) {
                    Image(imageVector = Icons.Rounded.Add, contentDescription = null)
                }
            }
        ) {
            Card(Modifier.padding(it)) {
                val noteViewModel: NoteViewModel = viewModel()
                LazyColumn{
                    Log.i("size Home",  noteViewModel.size().toString())
                    items(items= noteViewModel.getAllNote()) {
                        NoteRow(it)
                    }
                }
            }
        }
    }

}
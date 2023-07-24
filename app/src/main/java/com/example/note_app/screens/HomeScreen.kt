package com.example.note_app.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note_app.components.NoteRow
import com.example.note_app.model.Note
import com.example.note_app.viewModel.NoteViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController? = null,context: Context,noteViewModel: NoteViewModel?=null ) {




    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        elevation = 10.dp
    ) {
        Scaffold(topBar = {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(4.dp)) {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(top = 50.dp, start = 5.dp, bottom = 5.dp)
                )
            }

        },
            floatingActionButton = {

                Button(
                    onClick = {
                        navController?.currentBackStackEntry?.savedStateHandle?.set(key="note_obj", value = null)
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
            Divider(
                Modifier
                    .border(border = BorderStroke(3.dp, Color.DarkGray))
                    .padding(5.dp))
            Card(Modifier.padding(it)) {
                val noteList: List<Note>? =noteViewModel?.noteList?.collectAsState()?.value
                if(noteList!=null){
                    LazyColumn{
                        items(items=noteList.reversed()){
                            NoteRow(note = it, context =context ,noteViewModel=noteViewModel, onNoteClick ={
                                navController?.currentBackStackEntry?.savedStateHandle?.set(key="note_obj", value = it)
                                navController?.navigate("NoteScreen")
                            } )
                        }
                    }
                }
            }
        }
    }

}
//@Composable
//fun ShowRow(noteList: List<Note>, context: Context?) {
//    LazyColumn{
//        items (noteList){
//            if(context!=null){
//               NoteRow(note = it, context = context){
//                   navController.currentBackStackEntry?.savedStateHandle?.set(key="note_obj", value = it)
//               }
//
//            }
//        }
//    }
//}

//fun ShowNote(it: Note, context: Context) {
//    GlobalScope.launch {
//        NoteRow(note = it, context = )
//    }
//}


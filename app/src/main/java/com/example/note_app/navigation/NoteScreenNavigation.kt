package com.example.note_app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note_app.model.Note
import com.example.note_app.screens.HomeScreen
import com.example.note_app.screens.NoteScreen
import com.example.note_app.viewModel.NoteViewModel


@Composable
fun NoteScreenNavigation(context:Context,noteViewModel: NoteViewModel){


    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen" ){
        composable("HomeScreen"){

            HomeScreen(navController,context,noteViewModel)

        }
        composable("NoteScreen") {
            val noteObj=navController.previousBackStackEntry?.savedStateHandle?.get<Note>("note_obj")
            NoteScreen(navController,context,noteViewModel,noteObj)

        }

    }
}
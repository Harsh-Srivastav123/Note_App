package com.example.note_app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note_app.screens.HomeScreen
import com.example.note_app.screens.NoteScreen
import com.example.note_app.viewModel.NoteViewModel


@Composable
fun NoteScreenNavigation(context:Context){


    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen" ){
        composable("HomeScreen"){

            HomeScreen(navController,context)
        }
        composable("NoteScreen") {
            NoteScreen(navController,context)
        }

    }
}
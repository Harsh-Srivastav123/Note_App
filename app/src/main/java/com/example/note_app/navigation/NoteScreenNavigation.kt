package com.example.note_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note_app.screens.HomeScreen
import com.example.note_app.screens.NoteScreen


@Composable
fun NoteScreenNavigation(){


    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen" ){
        composable("HomeScreen"){
            HomeScreen(navController)
        }
        composable("NoteScreen") {
            NoteScreen()
        }

    }
}
package com.example.note_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note_app.screens.NoteScreen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NoteScreenNavigation(){

    val systemUiController: SystemUiController = rememberSystemUiController()

    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen"){
        composable("HomeScreen"){
            NoteScreen()
        }
    }
}
 package com.example.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_app.navigation.NoteScreenNavigation
import com.example.note_app.ui.theme.Note_AppTheme
import com.example.note_app.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint




 @AndroidEntryPoint

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Note_AppTheme {
                val noteViewModel:NoteViewModel by viewModels()
                // A surface container using the 'background' color from the theme
                NoteScreenNavigation(this,noteViewModel)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Note_AppTheme {
    }
}
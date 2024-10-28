// 6. Création de l'activité principale
package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.core.view.WindowCompat
import com.example.todolistapp.ui.theme.TodoListAppTheme
import com.example.todolistapp.view.components.TodoApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.toArgb

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoListAppTheme {
                val colorScheme = MaterialTheme.colorScheme
                SideEffect {
                    window.statusBarColor = Color.Black.toArgb()
                    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
                }

                // 6.2 Configuration de l'interface utilisateur principale
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { 
                                Text(
                                    "My Todo List",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0D47A1) // Un bleu plus foncé
                            )
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TodoApp()
                    }
                }
            }
        }
    }
}

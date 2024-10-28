package com.example.todolistapp.view.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todolistapp.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoList(viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState()
    var newTodoTitle by remember { mutableStateOf("") }
    var newTodoDueDate by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TextField(
            value = newTodoTitle,
            onValueChange = { newTodoTitle = it },
            label = { Text("Nouvelle tâche") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTodoDueDate,
                onValueChange = { newTodoDueDate = it },
                label = { Text("Date d'échéance (JJ/MM/AAAA)") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            DatePickerButton(
                onDateSelected = { selectedDate ->
                    newTodoDueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
                }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = {
                if (newTodoTitle.isNotBlank()) {
                    val dueDate = try {
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(newTodoDueDate)?.time
                    } catch (e: Exception) {
                        null
                    }
                    viewModel.addTodo(newTodoTitle, dueDate)
                    newTodoTitle = ""
                    newTodoDueDate = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)), // Même couleur que la barre supérieure
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ajouter", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todos, key = { it.id }) { todo ->
                TodoItem(todo, viewModel)
            }
        }
    }
}

@Composable
fun DatePickerButton(onDateSelected: (Date) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    IconButton(onClick = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }) {
        Text("📅")
    }
}

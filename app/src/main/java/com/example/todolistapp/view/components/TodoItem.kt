package com.example.todolistapp.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolistapp.model.Todo
import com.example.todolistapp.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoItem(todo: Todo, viewModel: TodoViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = { viewModel.toggleTodoCompletion(todo) },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            )
            todo.dueDate?.let {
                Text(
                    text = "Due: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(it))}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        IconButton(
            onClick = { viewModel.deleteTodo(todo) },
            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("❌")
        }
    }
}

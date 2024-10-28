package com.example.todolistapp.view.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.viewmodel.TodoViewModel

@Composable
fun TodoApp(viewModel: TodoViewModel = viewModel()) {
    TodoList(viewModel = viewModel)
}

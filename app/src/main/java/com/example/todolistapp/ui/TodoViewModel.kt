package com.example.todolistapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.model.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val repository = TodoRepository()

    val todos: StateFlow<List<Todo>> = repository.allTodos
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodo(title: String, dueDate: Long? = null) {
        viewModelScope.launch {
            repository.insertTodo(Todo(title = title, dueDate = dueDate))
        }
    }

    fun toggleTodoCompletion(todo: Todo) {
        viewModelScope.launch {
            val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.updateTodo(updatedTodo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}

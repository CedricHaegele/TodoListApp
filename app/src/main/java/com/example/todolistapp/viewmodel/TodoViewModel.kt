package com.example.todolistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.model.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val repository = TodoRepository()

    // Exposition des tâches sous forme de StateFlow
    val todos: StateFlow<List<Todo>> = repository.allTodos
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Ajout d'une nouvelle tâche
    fun addTodo(title: String, dueDate: Long? = null) {
        viewModelScope.launch {
            repository.insertTodo(Todo(title = title, dueDate = dueDate))
        }
    }

    // Basculement de l'état de complétion d'une tâche
    fun toggleTodoCompletion(todo: Todo) {
        viewModelScope.launch {
            val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
            repository.updateTodo(updatedTodo)
        }
    }

    // Suppression d'une tâche
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}

package com.example.todolistapp.data

import com.example.todolistapp.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import io.objectbox.kotlin.flow
import io.objectbox.query.QueryBuilder

class TodoRepository {
    private val todoBox = ObjectBox.todoBox()

    // Récupération de toutes les tâches sous forme de Flow
    val allTodos: Flow<List<Todo>> = todoBox.query().build().flow().map { it.toList() }

    // Insertion d'une nouvelle tâche
    suspend fun insertTodo(todo: Todo) {
        todoBox.put(todo)
    }

    // Mise à jour d'une tâche existante
    suspend fun updateTodo(todo: Todo) {
        todoBox.put(todo)
    }

    // Suppression d'une tâche
    suspend fun deleteTodo(todo: Todo) {
        todoBox.remove(todo)
    }
}

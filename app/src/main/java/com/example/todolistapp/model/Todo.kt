package com.example.todolistapp.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Todo(
    @Id var id: Long = 0,
    var title: String,
    var isCompleted: Boolean = false,
    var dueDate: Long? = null
)

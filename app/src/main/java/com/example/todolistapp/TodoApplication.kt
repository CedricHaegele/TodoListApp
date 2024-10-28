package com.example.todolistapp

import android.app.Application
import com.example.todolistapp.data.ObjectBox

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}

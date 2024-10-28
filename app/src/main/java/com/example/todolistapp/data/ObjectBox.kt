package com.example.todolistapp.data

import android.content.Context
import com.example.todolistapp.model.MyObjectBox
import com.example.todolistapp.model.Todo
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import io.objectbox.kotlin.boxFor


object ObjectBox {
    lateinit var store: BoxStore
        private set

    fun init(context: Context) {
        store = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        if (com.example.todolistapp.BuildConfig.DEBUG) {
            AndroidObjectBrowser(store).start(context.applicationContext)
        }
    }

    fun todoBox() = store.boxFor<Todo>()
}

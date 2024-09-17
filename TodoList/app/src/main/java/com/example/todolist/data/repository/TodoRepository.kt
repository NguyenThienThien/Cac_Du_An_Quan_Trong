package com.example.todolist.data.repository

import com.example.todolist.data.models.Todo
import com.example.todolist.data.network.ApiService
import retrofit2.Response

class TodoRepository(private val apiService: ApiService){
    suspend fun getTodos(): Response<List<Todo>> {
        return apiService.getTodos()
    }

    suspend fun addTodo(todo: Todo): Response<Todo> {
        return apiService.addTodo(todo)
    }

    suspend fun updateTodo(todoId: String, todo: Todo): Response<Todo> {
        return apiService.updateTodo(todoId, todo)
    }

    suspend fun deleteTodo(todoId: String): Response<Unit> {
        return apiService.deleteTodo(todoId)
    }
}
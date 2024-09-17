package com.example.todolist.data.network

import com.example.todolist.data.models.Todo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("get-todos")
    suspend fun getTodos(): Response<List<Todo>>

    @POST("add-todos")
    suspend fun addTodo(@Body todo: Todo): Response<Todo>

    @PUT("update-todos/{id}")
    suspend fun updateTodo(@Path("id") todoId: String, @Body todo: Todo): Response<Todo>

    @DELETE("delete-todos/{id}")
    suspend fun deleteTodo(@Path("id") todoId: String): Response<Unit>
}
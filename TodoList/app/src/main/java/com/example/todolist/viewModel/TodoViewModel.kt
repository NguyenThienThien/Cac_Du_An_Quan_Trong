package com.example.todolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.models.Todo
import com.example.todolist.data.network.RetrofitService
import com.example.todolist.data.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel(){
    private val apiService = RetrofitService().apiService
    private val todoRepository = TodoRepository(apiService)

    private val _todos = MutableLiveData<List<Todo>>()
    val todo: LiveData<List<Todo>> get() = _todos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchTodos(){
        viewModelScope.launch {
            try {
                val response = todoRepository.getTodos()
                if(response.isSuccessful){
                    _todos.value = response.body()
                }else{
                    _error.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Unknown Error: ${e.message}"
            }
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            try {
                val response = todoRepository.addTodo(todo)
                if(response.isSuccessful){
                    fetchTodos()
                }else{
                    _error.value = "Error: ${response.message()}"
                }
            }catch (e: Exception){
                _error.value = "Unknown Error: ${e.message}"
            }
        }
    }

    fun updateTodo(id: String, todo: Todo){
        viewModelScope.launch {
            try {
                val response = todoRepository.updateTodo(id, todo)
                if(response.isSuccessful){
                    fetchTodos()
                }else{
                    _error.value = "Error: ${response.message()}"
                }
            }catch (e: Exception){
                _error.value = "Unknown Error: ${e.message}"
            }
        }
    }

    fun deleteTodo(id: String){
        viewModelScope.launch {
            try {
                val response = todoRepository.deleteTodo(id)
                if(response.isSuccessful){
                    fetchTodos()
                }else{
                    _error.value = "Error: ${response.message()}"
                }
            }catch (e: Exception){
                _error.value = "Unknown Error: ${e.message}"
            }
        }
    }


}
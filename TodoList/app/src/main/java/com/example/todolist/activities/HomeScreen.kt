package com.example.todolist.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.activities.component.AddTodoDialog
import com.example.todolist.activities.component.TodoItem
import com.example.todolist.data.models.Todo
import com.example.todolist.viewModel.TodoViewModel

@Composable
fun HomeScreen(navController: NavController, todoViewModel: TodoViewModel = viewModel()) {
    val todos by todoViewModel.todo.observeAsState(emptyList())
    val error by todoViewModel.error.observeAsState("")

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        todoViewModel.fetchTodos()
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp)
        ) {
            Text(
                text = "Todolist",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            )

            LazyColumn {
                items(todos) { todo ->
                    TodoItem(todo)
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "+",
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }

    if (showDialog) {
        AddTodoDialog(
            onAddTodo = { newTodo ->
                todoViewModel.addTodo(newTodo)
            },
            onDismiss = { showDialog = false }
        )
    }
}



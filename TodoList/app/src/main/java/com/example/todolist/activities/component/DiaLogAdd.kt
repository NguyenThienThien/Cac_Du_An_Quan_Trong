package com.example.todolist.activities.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.todolist.data.models.Todo

@Composable
fun AddTodoDialog(onAddTodo: (Todo) -> Unit, onDismiss: () -> Unit) {
    var task by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add New Todo") },
        text = {
            Column {
                // Các trường nhập liệu cho todo mới
                TextField(
                    value = task,
                    onValueChange = { task = it },
                    label = { Text("Task") }
                )
                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") }
                )
                TextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") }
                )
                TextField(
                    value = priority.toString(),
                    onValueChange = { priority = it.toIntOrNull() ?: 0 },
                    label = { Text("Priority") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newTodo = Todo(
                        id = "",
                        task = task,
                        isCompleted = false,
                        dueDate = dueDate.toLongOrNull(),
                        notes = notes,
                        priority = priority
                    )
                    onAddTodo(newTodo)
                    onDismiss()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

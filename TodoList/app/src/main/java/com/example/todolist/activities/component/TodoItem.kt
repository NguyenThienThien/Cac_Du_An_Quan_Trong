package com.example.todolist.activities.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.models.Todo
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TodoItem(todo: Todo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(3.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = todo.task,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (todo.dueDate != null) {
            Text(
                text = "Due: ${SimpleDateFormat("dd/MM/yyyy").format(Date(todo.dueDate))}",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (todo.notes != null) {
            Text(
                text = "Notes: ${todo.notes}",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "Priority: ${when (todo.priority) {
                0 -> "Low"
                1 -> "Medium"
                2 -> "High"
                else -> "Unknown"
            }}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = if (todo.isCompleted) "✓ Completed" else "✗ Not Completed",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (todo.isCompleted) Color.Green else Color.Red
        )
    }
}
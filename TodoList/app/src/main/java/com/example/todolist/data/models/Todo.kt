package com.example.todolist.data.models

data class Todo(
    val id: String,
    val task: String, // một chuỗi văn bản để mô tả nội dung công việc
    val isCompleted: Boolean = false, // trạng thái hoàn thành của công việc
    val createdAt: Long = System.currentTimeMillis(), // lưu trữ thời gian tạo công việc
    val dueDate: Long? = null, // lưu trữ thời gian hết hạn công việc
    val priority: Int = 0, // 0: thấp, 1: trung bình, 2: cao mức độ ưu tiên của công việc
    val notes: String? = null, // ghi chú
    val category: String? = null // doanh mục
)
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const todoSchema = new Schema({
    task: { type: String, required: true },
    isCompleted: { type: Boolean, default: false },
    createdAt: { type: Number, default: Date.now },
    dueDate: { type: Number, default: null },
    priority: { type: Number, default: 0 },
    notes: { type: String, default: null },
    category: { type: String, default: null }
});

const Todo = mongoose.model('Todo', todoSchema);

module.exports = Todo;

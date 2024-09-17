var express = require('express')
var router = express.Router();

const Todo = require('../models/todo')

// Create a new todo
router.post('/add-todos', async (req, res) => {
    try {
        const todo = new Todo({
            task: req.body.task,
            isCompleted: req.body.isCompleted || false,
            createdAt: Date.now(),
            dueDate: req.body.dueDate || null,
            priority: req.body.priority || 0,
            notes: req.body.notes || null,
            category: req.body.category || null
        });
        const savedTodo = await todo.save();
        res.status(201).json(savedTodo);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Get all todos
router.get('/get-todos', async (req, res) => {
    try {
        const todos = await Todo.find();
        res.json(todos);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Get a single todo by ID
router.get('/get-todosById/:id', async (req, res) => {
    try {
        const todo = await Todo.findById(req.params.id);
        if (!todo) return res.status(404).json({ message: 'Todo not found' });
        res.json(todo);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Update a todo by ID
router.put('/update-todos/:id', async (req, res) => {
    try {
        const todo = await Todo.findById(req.params.id);
        if (!todo) return res.status(404).json({ message: 'Todo not found' });

        todo.task = req.body.task || todo.task;
        todo.isCompleted = req.body.isCompleted || todo.isCompleted;
        todo.dueDate = req.body.dueDate || todo.dueDate;
        todo.priority = req.body.priority || todo.priority;
        todo.notes = req.body.notes || todo.notes;
        todo.category = req.body.category || todo.category;

        const updatedTodo = await todo.save();
        res.json(updatedTodo);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Delete a todo by ID
router.delete('/delete-todos/:id', async (req, res) => {
    try {
        const todo = await Todo.findById(req.params.id);
        if (!todo) return res.status(404).json({ message: 'Todo not found' });

        await todo.remove();
        res.json({ message: 'Todo deleted' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;
package com.ty.ToDo.service;

import com.ty.ToDo.entity.Todo;
import com.ty.ToDo.entity.User;
import com.ty.ToDo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getTodosByStatus(boolean completed) {
        return todoRepository.findByCompleted(completed);
    }
    
    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getUser() != null && todo.getUser().getId().equals(user.getId()))
                .toList();
    }

    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTask(todoDetails.getTask());
            todo.setDescription(todoDetails.getDescription());
            todo.setCompleted(todoDetails.isCompleted());
            todo.setUpdatedAt(LocalDateTime.now());
            return todoRepository.save(todo);
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
    
    public void save(Todo todo) {
        todoRepository.save(todo);
    }
    
    public void updateTask(Long id, String task) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTask(task);
            todo.setUpdatedAt(LocalDateTime.now());
            todoRepository.save(todo);
        }
    }
    
    public void updateTaskWithDescription(Long id, String task, String description) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTask(task);
            todo.setDescription(description);
            todo.setUpdatedAt(LocalDateTime.now());
            todoRepository.save(todo);
        }
    }
}

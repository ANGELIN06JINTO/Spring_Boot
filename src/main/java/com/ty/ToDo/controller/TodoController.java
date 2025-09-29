package com.ty.ToDo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.ToDo.entity.Todo;
import com.ty.ToDo.entity.User;
import com.ty.ToDo.service.TodoService;
import com.ty.ToDo.service.UserService;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping
    public String viewTodos(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("todos", todoService.getTodosByUser(user));
        model.addAttribute("newTodo", new Todo());
        return "todo";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Todo todo, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        todo.setUser(user);
        todoService.save(todo);
        return "redirect:/todos";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestParam String task, @RequestParam(required = false) String description) {
        todoService.updateTaskWithDescription(id, task, description);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}

package com.spring.redis.learning.springredislearning.controller;

import com.spring.redis.learning.springredislearning.entity.Todo;
import com.spring.redis.learning.springredislearning.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepo repo;

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return repo.save(todo);
    }

    @PostMapping("/bulk-insert")
    public List<Todo> saveAll(@RequestBody List<Todo> todos) {
        repo.saveAll(todos);
        return getAllTodos();
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Object findTodo(@PathVariable Long id) {
//        return todoRepository.findById(id).orElse(null);
        return repo.findToDoById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTodo(@PathVariable Long id) {
        return repo.deleteById(id);
    }
}

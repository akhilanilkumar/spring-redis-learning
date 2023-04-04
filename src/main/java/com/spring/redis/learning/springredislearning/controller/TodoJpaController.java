package com.spring.redis.learning.springredislearning.controller;

import com.spring.redis.learning.springredislearning.entity.Todo;
import com.spring.redis.learning.springredislearning.exception.NoSuchDataExistException;
import com.spring.redis.learning.springredislearning.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
@Slf4j
public class TodoJpaController {

    private final TodoRepository todoRepository;

    @GetMapping
    public List<Todo> findAll() {
        return ((List<Todo>) todoRepository.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Todo", unless = "#result == null")
    public Todo findById(@PathVariable Long id) throws NoSuchDataExistException {
        log.info("Executing findById");
        return todoRepository.findById(id).orElseThrow(() -> new NoSuchDataExistException("No records exists with id: " + id));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Todo")
    public void deleteById(@PathVariable Long id) {
        todoRepository.deleteById(id);
        log.info("Data deleted successfully!");
    }

    @PostMapping
    public void saveAll(@RequestBody List<Todo> todos) {
        todoRepository.saveAll(todos);
        log.info("Data inserted successfully!");
    }
}

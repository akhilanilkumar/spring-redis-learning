package com.spring.redis.learning.springredislearning.repository;

import com.spring.redis.learning.springredislearning.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepo {

    private final RedisTemplate redisTemplate;

    private final String HASH_KEY = "Todo";

    public Todo save(Todo toDo) {
        redisTemplate.opsForHash().put(HASH_KEY, toDo.getId(), toDo);
        return toDo;
    }

    public String saveAll(List<Todo> toDos) {
        //Map<Long, Todo> todoMap = toDos.stream().collect(Collectors.toMap(Todo::getId, todo -> todo));
        //redisTemplate.opsForHash().putAll(HASH_KEY, todoMap);
        return "Record(s) inserted successfully";
    }

    public List<Todo> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Object findToDoById(Long id) {
        return redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public boolean deleteById(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return true;
    }
}

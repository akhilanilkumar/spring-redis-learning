package com.spring.redis.learning.springredislearning.repository;

import com.spring.redis.learning.springredislearning.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}

package com.spring.redis.learning.springredislearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Todo2")
public class Todo implements Serializable {
    @Id
    @Indexed
    private Long id;

    private Long userId;

    private String title;

    private boolean completed;
}

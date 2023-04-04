package com.spring.redis.learning.springredislearning.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchDataExistException extends Throwable {

    public NoSuchDataExistException(String message) {
        super(message);
    }
}

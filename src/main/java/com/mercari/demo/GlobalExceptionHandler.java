package com.mercari.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 1. "나, 모든 에러를 감시하는 관제탑이야." (선언)
public class GlobalExceptionHandler {

    // 2. "IllegalArgumentException이라는 폭탄이 떨어지면 내가 잡는다!" (낚아채기)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleArgumentException(IllegalArgumentException ex) {

        // 3. 에러 내용을 예쁘게 포장한다. (JSON 만들기)
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", 400); // 400 = 너의 잘못이다. (Bad Request)
        errorBody.put("error", "잘못된 요청값");
        errorBody.put("message", ex.getMessage()); // 아까 네가 쓴 "가격은 0원 이상..." 메시지

        // 4. 우아하게 반환한다.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);
    }
}
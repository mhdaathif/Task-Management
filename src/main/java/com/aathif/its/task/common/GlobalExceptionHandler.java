package com.aathif.its.task.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException accessDeniedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(accessDeniedException.getMessage());
    }
}

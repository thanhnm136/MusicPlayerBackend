package com.is1423.musicplayerbackend.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionTranslator extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
        ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(commonReturnException(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex,
        WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("title", "API not Valid");
        body.put("field", "Not Correct");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    private Map<String, Object> commonReturnException(BaseException base) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("entityName", base.getEntityName());
        body.put("title", base.getTitle());
        body.put("errorKey", base.getErrorKey());
        return body;
    }
}

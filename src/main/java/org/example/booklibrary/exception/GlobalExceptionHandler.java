package org.example.booklibrary.exception;

import jakarta.validation.ConstraintViolationException;
import org.example.booklibrary.dto.response.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleBookNotFoundException(BookNotFoundException ex) {
    ErrorDetails errorResponse =
        new ErrorDetails(ex.getRequestId(), ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // Handle ConstraintViolationException (which will include @Email validation errors)
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations()
        .forEach(
            violation -> {
              String fieldName = violation.getPropertyPath().toString();
              String errorMessage = violation.getMessage();
              errors.put(fieldName, errorMessage);
            });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}

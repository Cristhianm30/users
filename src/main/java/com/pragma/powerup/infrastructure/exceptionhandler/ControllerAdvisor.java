package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.InvalidCellPhoneException;
import com.pragma.powerup.domain.exception.InvalidEmailException;
import com.pragma.powerup.domain.exception.InvalidOwnerAgeException;
import com.pragma.powerup.domain.exception.InvalidUserFieldsException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> invalidEmailFoundException(
            InvalidEmailException invalidEmailException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BAD_EMAIL.getMessage()));
    }

    @ExceptionHandler(InvalidOwnerAgeException.class)
    public ResponseEntity<Map<String, String>> invalidOwnerAgeException(
            InvalidOwnerAgeException invalidOwnerAgeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_AGE.getMessage()));
    }

    @ExceptionHandler(InvalidCellPhoneException.class)
    public ResponseEntity<Map<String, String>> invalidCellPhoneException(
            InvalidCellPhoneException invalidCellPhoneException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CELLPHONE.getMessage()));
    }

    @ExceptionHandler(InvalidUserFieldsException.class)
    public ResponseEntity<Map<String, String>> invalidUserFieldsException(
            InvalidUserFieldsException invalidUserFieldsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_USER_FIELDS.getMessage()));
    }
    
}
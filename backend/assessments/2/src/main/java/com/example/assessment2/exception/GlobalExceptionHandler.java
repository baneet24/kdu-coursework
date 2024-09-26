package com.example.assessment2.exception;

import com.example.assessment2.dto.ErrorDTO;
import com.example.assessment2.exception.custom.MyCustomException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errordto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<ErrorDTO> badRequestException(BadRequestException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + "[Bad Request Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> exceptions(Exception ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + " [Parent Exception]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errordto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

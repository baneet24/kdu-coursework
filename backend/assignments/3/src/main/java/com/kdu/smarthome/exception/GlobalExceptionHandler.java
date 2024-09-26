package com.kdu.smarthome.exception;

import com.kdu.smarthome.dto.ErrorDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={AccessDeniedException.class})
    public ResponseEntity<ErrorDTO> accessDeniedException(AccessDeniedException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + "[Access Denied Exception]", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errordto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value={JsonMapperException.class})
    public ResponseEntity<ErrorDTO> jsonMapperException(JsonMapperException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + "[Json Mapper Exception]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errordto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<ErrorDTO> badRequestException(BadRequestException ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + "[Bad Request Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> exceptions(Exception ex){
        ErrorDTO errordto = new ErrorDTO(ex.getMessage() + " [Parent Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }
}

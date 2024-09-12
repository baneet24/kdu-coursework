package com.kdu.caching.exception;

import com.kdu.caching.dto.ErrorDTO;
import com.kdu.caching.exception.customexceptions.InvalidAddressException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={InvalidAddressException.class})
    public static ResponseEntity<ErrorDTO> invalidAddressException(InvalidAddressException ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={BadRequestException.class})
    public static ResponseEntity<ErrorDTO> badRequestException(BadRequestException ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDTO> exception(Exception ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}

package com.scaler.ProductServiceDemo.controlleradvice;

import com.scaler.ProductServiceDemo.dtos.ExceptionDto;
import com.scaler.ProductServiceDemo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic Exception has occurred");
        exceptionDto.setSolution("No solution exists");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "NullPointerException has occurred",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "ArrayIndexOutOfBounds Exception has occurred",
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("ProductNotFoundException has occurred");
        exceptionDto.setSolution("Please try again");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }
}

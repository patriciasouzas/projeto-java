package br.com.loja.projetojavaglobo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handlerProductNotFound(ProductNotFoundException e){
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }
    @ExceptionHandler(DescriptionLengthException.class)
    public ResponseEntity handlerLengthDescription(DescriptionLengthException e){
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }
    @ExceptionHandler(NameLengthException.class)
    public ResponseEntity handlerLengthName(NameLengthException e){
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }
    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity handlerInvalidValue(InvalidValueException e){
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }
}

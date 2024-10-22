package ecommerce.smallecomerce.exception;

import ecommerce.smallecomerce.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllException(Exception e) {
        Response errResponse = Response.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errResponse, INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException e) {
        Response errResponse = Response.builder()
                .status(NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return  new ResponseEntity<>(errResponse, NOT_FOUND);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handleInvalidCredentialsException(InvalidCredentialsException e) {
        Response errResponse = Response.builder()
                .status(BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errResponse,BAD_REQUEST);
    }
}

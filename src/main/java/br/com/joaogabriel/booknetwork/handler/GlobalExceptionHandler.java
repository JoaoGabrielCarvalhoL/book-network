package br.com.joaogabriel.booknetwork.handler;

import br.com.joaogabriel.booknetwork.exception.BookNotAvailableException;
import br.com.joaogabriel.booknetwork.exception.IllegalBorrowException;
import br.com.joaogabriel.booknetwork.exception.ResourceAlreadyInUseException;
import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.handler.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse("Bad Request", exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyInUseException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyInUseException(ResourceAlreadyInUseException exception) {
        ExceptionResponse response = new ExceptionResponse("Bad Request", exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotAvailableException(BookNotAvailableException exception) {
        ExceptionResponse response = new ExceptionResponse("Conflict", exception.getMessage(),
                HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalBorrowException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalBorrowException(IllegalBorrowException exception) {
        ExceptionResponse response = new ExceptionResponse("Bad Request", exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fields = ex.getFieldErrors();
        for (FieldError field : fields) {
            errors.put(field.getField(), field.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse response = new ExceptionResponse("Internal Server Error", exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package dev.sangco.layoutproduct.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("IllegalArgumentException");
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

}

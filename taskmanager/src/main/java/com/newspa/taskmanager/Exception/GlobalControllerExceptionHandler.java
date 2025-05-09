package com.newspa.taskmanager.Exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.coyote.BadRequestException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleConversionFailedException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage() + "SSS", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex){
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .error(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);

    }







    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex){
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .error(ex.getMessage())
                .build(), ex.getHttpStatus());

    }
}

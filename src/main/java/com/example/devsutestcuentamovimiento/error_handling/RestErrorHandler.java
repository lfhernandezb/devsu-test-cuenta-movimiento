package com.example.devsutestcuentamovimiento.error_handling;

import com.example.devsutestcuentamovimiento.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    // errors defined in Spring Boot

    //@Override
    protected ResponseEntity<Object> handleRestClientException(
            RestClientException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        //return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // custom errors
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(
            AccountNotFoundException ex, WebRequest request) {

        logger.info("AccountNotFoundException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFoundException(
            ClientNotFoundException ex, WebRequest request) {

        logger.info("ClientNotFoundException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenderNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            GenderNotFoundException ex, WebRequest request) {

        logger.info("GenderNotFoundException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            InsufficientFundsException ex, WebRequest request) {

        logger.info("InsufficientFundsException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingConfigurationValueException.class)
    public ResponseEntity<Object> handleMissingConfigurationValueException(
            MissingConfigurationValueException ex, WebRequest request) {

        logger.info("MissingConfigurationValueException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MovementTypeNotFoundException.class)
    public ResponseEntity<Object> handleMovementTypeNotFoundException(
            MovementTypeNotFoundException ex, WebRequest request) {

        logger.info("MovementTypeNotFoundException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovementTypeNotSupportedException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            MovementTypeNotSupportedException ex, WebRequest request) {

        logger.info("MovementTypeNotSupportedException thrown");

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(RestClientException.class)
    public ResponseEntity<Object> handleRestClientException(
            RestClientException ex, WebRequest request) {

        logger.info("RestClientException thrown " + ex);

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(
            ExecutionException ex, WebRequest request) {

        logger.info("ExecutionException thrown " + ex);

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            Exception ex, WebRequest request) {

        logger.info("Exception thrown " + ex);

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("timestamp", LocalDateTime.now());

        if (ex.getMessage().contains("HttpClientErrorException$NotFound")) {
            body.put("error", "Entidad no encontrada");
        }
        else {
            body.put("error", ex.getMessage());
        }

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /*private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }*/

}

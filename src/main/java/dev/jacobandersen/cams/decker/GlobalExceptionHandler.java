package dev.jacobandersen.cams.decker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<Error> handleTypeMismatch(ServerWebInputException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity
                .badRequest()
                .body(new Error("bad_request", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGeneric(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Error("internal_server_error", ex.getMessage()));
    }
}

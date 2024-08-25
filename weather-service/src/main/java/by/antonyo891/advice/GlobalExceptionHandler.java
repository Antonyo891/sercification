package by.antonyo891.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<String> weatherException(ResponseStatusException e){
        log.warn(e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}

package com.raisetech.task10;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @RestControllerAdvice
    public class WebExceptionHandler {
        @ExceptionHandler(value = ResourceNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleNoResourceFound(
                ResourceNotFoundException e, HttpServletRequest request) {
            Map<String, String> body = Map.of(
                    "timestamp", ZonedDateTime.now().toString(),
                    "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                    "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                    "message", e.getMessage(),
                    "path", request.getRequestURI());
            return new ResponseEntity(body, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/names")
    public List<Name> getNames() {
        return nameService.findAll();
    }

    @GetMapping("/names/{id}")
    public Name getUser(@PathVariable("id") int id) throws Exception {
        return nameService.findById(id);
    }
}

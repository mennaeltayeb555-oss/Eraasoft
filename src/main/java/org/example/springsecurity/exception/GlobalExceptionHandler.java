package org.example.springsecurity.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<?> notFound(NoSuchElementException e){return body(404,"Not Found",e.getMessage());}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> badRequest(IllegalArgumentException e){return body(400,"Bad Request",e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){return body(400,"Validation Failed","Check request fields");}
 @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class) ResponseEntity<?> conflict(Exception e){return body(409,"Conflict","A unique or database constraint was violated");}
 private ResponseEntity<Map<String,Object>> body(int status,String error,String message){return ResponseEntity.status(status).body(Map.of("timestamp",Instant.now().toString(),"status",status,"error",error,"message",message==null?error:message));}
}

package org.swpractice.validation;

import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class GlobalExceptionHandler {

//    @ExceptionHandler
    @ExceptionHandler(TransactionSystemException.class)
    ResponseEntity handleTransactionSystemException(TransactionSystemException e) {
        System.out.println("Got here: handleException: " + e.getRootCause());
        if (e.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e.getRootCause();
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            String errors = constraintViolations.stream().map(v -> v.getMessage()).reduce(", ", String::concat);
            return new ResponseEntity<>("Not Valid due to validation error: " + errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Not Valid due to error: " + e.getRootCause(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity handleException(Exception e) {
        System.out.println("Got here: handleException: " + e.getCause().getMessage());
//        if (e.getRootCause() instanceof ConstraintViolationException) {
//            ConstraintViolationException ex = (ConstraintViolationException) e.getRootCause();
//            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//            String errors = constraintViolations.stream().map(v -> v.getMessage()).reduce(", ", String::concat);
//            return new ResponseEntity<>("Not Valid due to validation error: " + errors, HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>("Not Valid due to general error: " + e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }
}

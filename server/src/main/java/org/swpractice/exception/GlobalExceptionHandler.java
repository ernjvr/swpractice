package org.swpractice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity handleTransactionSystemException(TransactionSystemException e) {
        logger.debug(String.format("handleTransactionSystemException: %s", e.getRootCause()));

        if (e.getRootCause() instanceof ConstraintViolationException) {
            final ConstraintViolationException ex = (ConstraintViolationException) e.getRootCause();
            final Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            final String errors = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, BAD_REQUEST);
        }
        return new ResponseEntity<>(e.getRootCause() == null? e.getMessage(): e.getRootCause().getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.debug(String.format("handleDataIntegrityViolationException: %s", e.getRootCause()));
        return new ResponseEntity<>("Data Integrity Violation", BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
        final String message = e.getMessage();
        logger.debug(String.format("handleResourceNotFoundException: %s", message));
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        final String message = e.getCause() != null ? e.getCause().getMessage(): e.getMessage();
        logger.debug(String.format("handleException: %s", message));
        return new ResponseEntity<>(String.format("Request Not Accepted: %s", message), BAD_REQUEST);
    }
}

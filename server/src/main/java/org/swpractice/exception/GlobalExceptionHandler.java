package org.swpractice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

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
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(e.getRootCause() == null? e.getMessage(): e.getRootCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        logger.debug(String.format("handleException: %s", e.getCause().getMessage()));
        return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }
}

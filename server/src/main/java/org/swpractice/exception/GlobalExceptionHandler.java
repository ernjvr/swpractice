package org.swpractice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity handleTransactionSystemException(TransactionSystemException e) {
        logger.debug(String.format("handleTransactionSystemException: %s", e.getRootCause()));

        if (e.getRootCause() instanceof ConstraintViolationException) {
            return new ResponseEntity<>("Constraint Violation", BAD_REQUEST);
        }
        return new ResponseEntity<>("Transaction System Error", BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.debug(String.format("handleDataIntegrityViolationException: %s", e.getRootCause()));
        return new ResponseEntity<>("Data Integrity Violation", BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        logger.debug(String.format("handleException: %s", e.getCause().getMessage()));
        return new ResponseEntity<>("Request Not Accepted", BAD_REQUEST);
    }
}

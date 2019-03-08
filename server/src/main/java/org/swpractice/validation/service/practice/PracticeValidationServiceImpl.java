package org.swpractice.validation.service.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swpractice.model.practice.Practice;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.swpractice.util.Constants.*;

@Service
public class PracticeValidationServiceImpl implements PracticeValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        final TypedQuery<Practice> namedQuery = createNamedQuery(fieldName);
        return verify(namedQuery, fieldName, value);
    }

    private TypedQuery<Practice> createNamedQuery(String fieldName) {
        if (PRACTICE_FIELD_NAME.equals(fieldName)) {
            return entityManager.createNamedQuery(PRACTICE_FIND_BY_NAME, Practice.class);
        } else {
            final String errorMessage = String.format(FIELD_NAME_NOT_SUPPORTED, fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }
}

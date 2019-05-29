package org.swpractice.validation.service.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swpractice.model.context.ContextEntityType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.swpractice.util.Constants.*;

@Service
public class ContextEntityTypeValidationServiceImpl implements ContextEntityTypeValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        return verify(fieldName, value);
    }

    @Override
    public TypedQuery<ContextEntityType> createNamedQuery(String fieldName) {
        if (CONTEXT_ENTITY_TYPE_FIELD_NAME.equals(fieldName)) {
            return entityManager.createNamedQuery(CONTEXT_ENTITY_TYPE_FIND_BY_NAME, ContextEntityType.class);
        } else {
            final String errorMessage = String.format(FIELD_NAME_NOT_SUPPORTED, fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }
}

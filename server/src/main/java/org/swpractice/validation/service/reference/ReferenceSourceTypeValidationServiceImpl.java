package org.swpractice.validation.service.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swpractice.model.reference.ReferenceSourceType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.swpractice.util.Constants.*;

@Service
public class ReferenceSourceTypeValidationServiceImpl implements ReferenceSourceTypeValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        return verify(fieldName, value);
    }

    @Override
    public TypedQuery<ReferenceSourceType> createNamedQuery(String fieldName) {
        if (REFERENCE_SOURCE_TYPE_FIELD_NAME.equals(fieldName)) {
            return entityManager.createNamedQuery(REFERENCE_SOURCE_TYPE_FIND_BY_NAME, ReferenceSourceType.class);
        } else {
            final String errorMessage = String.format(FIELD_NAME_NOT_SUPPORTED, fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }
}
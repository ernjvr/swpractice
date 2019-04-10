package org.swpractice.validation.service.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.model.practice.PracticeSubCategory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.swpractice.util.Constants.*;

@Service
public class PracticeSubCategoryValidationServiceImpl implements PracticeSubCategoryValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MappedInterceptor requestInterceptor;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        return verify(fieldName, value, requestInterceptor);
    }

    @Override
    public TypedQuery<PracticeSubCategory> createNamedQuery(String fieldName) {
        if (PRACTICE_SUB_CATEGORY_FIELD_NAME.equals(fieldName)) {
            return entityManager.createNamedQuery(PRACTICE_SUB_CATEGORY_FIND_BY_NAME, PracticeSubCategory.class);
        } else {
            final String errorMessage = String.format(FIELD_NAME_NOT_SUPPORTED, fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }
}

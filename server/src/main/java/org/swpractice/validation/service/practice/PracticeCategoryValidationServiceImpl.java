package org.swpractice.validation.service.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swpractice.model.practice.PracticeCategory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.swpractice.util.Constants.*;

@Service
public class PracticeCategoryValidationServiceImpl implements PracticeCategoryValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;
    private TypedQuery<PracticeCategory> namedQuery;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        createNamedQuery(fieldName);
        namedQuery.setParameter(fieldName, value);
        List<PracticeCategory> categories = namedQuery.getResultList();
        return !categories.isEmpty();
    }

    private void createNamedQuery(String fieldName) {
        if (PRACTICE_CATEGORY_FIELD_NAME.equals(fieldName)) {
            namedQuery = entityManager.createNamedQuery(PRACTICE_CATEGORY_FIND_BY_NAME, PracticeCategory.class);
        } else {
            final String errorMessage = String.format("Field name '%s' not supported.", fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }
}

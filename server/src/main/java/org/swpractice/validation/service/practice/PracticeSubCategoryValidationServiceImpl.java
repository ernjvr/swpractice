package org.swpractice.validation.service.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.model.practice.PracticeSubCategory;
import org.swpractice.util.ApiRequestInterceptor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
        final TypedQuery<PracticeSubCategory> namedQuery = createNamedQuery(fieldName);
        final ApiRequestInterceptor interceptor = (ApiRequestInterceptor) requestInterceptor.getInterceptor();
        final String requestMethod = interceptor.getRequestMethod();

        if (requestMethod.equalsIgnoreCase("PATCH")) {
            return verifyPatch(fieldName, value, namedQuery, interceptor);
        }
        return verify(namedQuery, fieldName, value);
    }

    private TypedQuery<PracticeSubCategory> createNamedQuery(String fieldName) {
        if (PRACTICE_SUB_CATEGORY_FIELD_NAME.equals(fieldName)) {
            return entityManager.createNamedQuery(PRACTICE_SUB_CATEGORY_FIND_BY_NAME, PracticeSubCategory.class);
        } else {
            final String errorMessage = String.format(FIELD_NAME_NOT_SUPPORTED, fieldName);
            logger.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }

    private boolean verifyPatch(String fieldName, Object value, TypedQuery<PracticeSubCategory> namedQuery, ApiRequestInterceptor interceptor) {
        final String uri = interceptor.getRequestURI();
        final String id = uri.substring(uri.lastIndexOf('/') + 1);
        final Long practiceId = Long.parseLong(id);
        return verify(namedQuery, fieldName, value, practiceId);
    }

    private boolean verify(TypedQuery<PracticeSubCategory> namedQuery, String fieldName, Object value, Long id) {
        namedQuery.setParameter(fieldName, value);
        List<PracticeSubCategory> result = namedQuery.getResultList();
        return !result.isEmpty() && !result.get(0).getId().equals(id);
    }
}

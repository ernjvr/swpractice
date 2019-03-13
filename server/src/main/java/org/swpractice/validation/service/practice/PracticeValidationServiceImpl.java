package org.swpractice.validation.service.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.model.practice.Practice;
import org.swpractice.util.ApiRequestInterceptor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.swpractice.util.Constants.*;

@Service
public class PracticeValidationServiceImpl implements PracticeValidationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MappedInterceptor requestInterceptor;

    @Override
    public boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException {
        logger.debug(String.format("columnValueExists: field name '%s', value %s", fieldName, value));
        final TypedQuery<Practice> namedQuery = createNamedQuery(fieldName);
        final ApiRequestInterceptor interceptor = (ApiRequestInterceptor) requestInterceptor.getInterceptor();
        final String requestMethod = interceptor.getRequestMethod();

        if (requestMethod.equalsIgnoreCase("PATCH")) {
            return verifyPatch(fieldName, value, namedQuery, interceptor);
        }
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

    private boolean verifyPatch(String fieldName, Object value, TypedQuery<Practice> namedQuery, ApiRequestInterceptor interceptor) {
        final String uri = interceptor.getRequestURI();
        final String id = uri.substring(uri.lastIndexOf('/') + 1);
        final Long practiceId = Long.parseLong(id);
        return verify(namedQuery, fieldName, value, practiceId);
    }

    private boolean verify(TypedQuery<Practice> namedQuery, String fieldName, Object value, Long id) {
        namedQuery.setParameter(fieldName, value);
        List<Practice> result = namedQuery.getResultList();
        return !result.isEmpty() && !result.get(0).getId().equals(id);
    }
}

package org.swpractice.validation.constraint.validator;

import org.springframework.web.servlet.handler.MappedInterceptor;
import org.swpractice.model.Identifier;
import org.swpractice.util.ApiRequestInterceptor;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.swpractice.util.Constants.PATCH;

public interface ColumnValueExists {

    boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException;

    <T> TypedQuery<T> createNamedQuery(String fieldName);

    default boolean verify(String fieldName, Object value, MappedInterceptor requestInterceptor) throws UnsupportedOperationException {
        final ApiRequestInterceptor interceptor = (ApiRequestInterceptor) requestInterceptor.getInterceptor();
        final String requestMethod = interceptor.getRequestMethod();

        if (requestMethod.equalsIgnoreCase(PATCH)) {
            return verifyPatch(fieldName, value, interceptor);
        }
        return verify(fieldName, value);
    }

    default <T> boolean verify(String fieldName, Object value) {
        final TypedQuery<T> namedQuery = createNamedQuery(fieldName);
        namedQuery.setParameter(fieldName, value);
        return !namedQuery.getResultList().isEmpty();
    }

    default <T extends Identifier> boolean verifyPatch(String fieldName, Object value, ApiRequestInterceptor interceptor) {
        final String uri = interceptor.getRequestURI();
        final String id = uri.substring(uri.lastIndexOf('/') + 1);
        final Long practiceId = Long.parseLong(id);
        final TypedQuery<T> namedQuery = createNamedQuery(fieldName);
        namedQuery.setParameter(fieldName, value);
        final List<T> result = namedQuery.getResultList();
        return !result.isEmpty() && !result.get(0).getId().equals(practiceId);
    }
}

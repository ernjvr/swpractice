package org.swpractice.validation.constraint.validator;

import javax.persistence.TypedQuery;

public interface ColumnValueExists {

    boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException;

    default <T> boolean verify(TypedQuery<T> namedQuery, String fieldName, Object value) {
        namedQuery.setParameter(fieldName, value);
        return !namedQuery.getResultList().isEmpty();
    }
}

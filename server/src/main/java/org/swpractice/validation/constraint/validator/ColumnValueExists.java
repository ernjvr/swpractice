package org.swpractice.validation.constraint.validator;

public interface ColumnValueExists {

    boolean columnValueExists(String fieldName, Object value) throws UnsupportedOperationException;
}

package org.swpractice.validation.constraints.validators;

public interface ColumnValueExists {
    boolean columnValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}

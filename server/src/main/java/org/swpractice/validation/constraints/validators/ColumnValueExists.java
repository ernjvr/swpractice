package org.swpractice.validation.constraints;

public interface ColumnValueExists {
    boolean columnValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}

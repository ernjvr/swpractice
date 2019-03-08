package org.swpractice.validation.constraint.validator;

import org.swpractice.util.ApplicationContextSupplier;
import org.swpractice.validation.constraint.annotation.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private ColumnValueExists validatorService;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        final Class<? extends ColumnValueExists> clazz = constraintAnnotation.service();
        final String qualifier = constraintAnnotation.serviceQualifier();
        fieldName = constraintAnnotation.fieldName();

        if (!qualifier.isEmpty()) {
            validatorService = (ColumnValueExists) ApplicationContextSupplier.getBean(qualifier, clazz);
        } else {
            validatorService = (ColumnValueExists) ApplicationContextSupplier.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !validatorService.columnValueExists(fieldName, value);
    }
}

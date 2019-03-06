package org.swpractice.validation.constraints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private ColumnValueExists validatorService;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        Class<? extends ColumnValueExists> clazz = constraintAnnotation.validatorService();
        fieldName = constraintAnnotation.fieldName();
        final String qualifier = constraintAnnotation.serviceQualifier();

        if (!qualifier.isEmpty()) {
            validatorService = applicationContext.getBean(qualifier, clazz);
        } else {
            validatorService = applicationContext.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !validatorService.columnValueExists(value, fieldName);
    }
}

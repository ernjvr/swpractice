package org.swpractice.validation.constraint.annotation;

import org.swpractice.validation.constraint.validator.ColumnValueExists;
import org.swpractice.validation.constraint.validator.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueValidator.class)
@Retention(RUNTIME)
public @interface Unique {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends ColumnValueExists> service();
    String serviceQualifier() default "";
    String fieldName();
}

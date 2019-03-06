package org.swpractice.validation.constraints;

import org.swpractice.validation.practice.PracticeCategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = PracticeCategoryValidator.class)
@Retention(RUNTIME)
public @interface Unique  {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends ColumnValueExists> validatorService();
    String serviceQualifier() default "";
    String fieldName();
}

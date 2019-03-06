package org.swpractice.validation.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.swpractice.model.practice.PracticeCategory;
import org.swpractice.repository.practice.PracticeCategoryRepository;
import org.swpractice.validation.constraints.annotations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PracticeCategoryValidator implements Prac {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PracticeCategoryRepository repository;

    @Override
    public void initialize(Unique uniqueAnnotation) {
        uniqueAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.debug(String.format("%s isValid value: %s.", this.getClass().getName(), value));
        System.out.println(String.format("%s isValid value: %s.", this.getClass().getName(), value));

//        if (repository != null && repository.findByName(value).isEmpty()) {
        if (repository != null) {
            List<PracticeCategory> names = repository.findByName(value);
            System.out.println("isValid JPA result size " + names.size() );
            return true;
        }
        return false;
    }
}

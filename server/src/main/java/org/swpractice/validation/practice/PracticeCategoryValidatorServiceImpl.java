package org.swpractice.validation.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swpractice.model.practice.PracticeCategory;
import org.swpractice.repository.practice.PracticeCategoryRepository;

import java.util.List;

@Service
public class PracticeCategoryValidatorServiceImpl implements PracticeCategoryValidatorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PracticeCategoryRepository repository;

    @Override
    public boolean columnValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        logger.debug(String.format("%s isValid value: %s.", this.getClass().getName(), value));
        System.out.println(String.format("%s isValid value: %s.", this.getClass().getName(), value));

//        if (repository != null && repository.findByName(value).isEmpty()) {
        if (repository != null) {
            List<PracticeCategory> names = repository.findByName(value.toString());
            System.out.println("isValid JPA result size " + names.size() );
            return names.isEmpty();
        }
        return false;
    }
}

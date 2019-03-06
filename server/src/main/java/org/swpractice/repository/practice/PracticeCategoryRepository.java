package org.swpractice.repository.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.swpractice.model.practice.PracticeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RepositoryRestResource(path = "/practice-category")
public interface PracticeCategoryRepository extends JpaRepository<PracticeCategory, Long> {
    List<PracticeCategory> findByName(@Param("name") String name);
}

package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.PracticeCategory;

import java.util.List;

import static org.swpractice.util.Constants.PRACTICE_CATEGORY_FIELD_NAME;
import static org.swpractice.util.Constants.PRACTICE_CATEGORY_PATH;

@CrossOrigin(origins = {"http://localhost:8081"}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_CATEGORY_PATH)
public interface PracticeCategoryRepository extends JpaRepository<PracticeCategory, Long> {
    List<PracticeCategory> findByName(@Param(PRACTICE_CATEGORY_FIELD_NAME) String name);
}

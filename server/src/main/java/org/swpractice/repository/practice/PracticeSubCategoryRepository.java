package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.Practice;
import org.swpractice.model.practice.PracticeSubCategory;

import java.util.List;

import static org.swpractice.util.Constants.*;

@CrossOrigin(origins = {"http://localhost:8081"}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_SUB_CATEGORY_PATH)
public interface PracticeSubCategoryRepository extends JpaRepository<PracticeSubCategory, Long> {
    List<Practice> findByName(@Param(PRACTICE_SUB_CATEGORY_FIELD_NAME) String name);
}

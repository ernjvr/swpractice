package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.PracticeSubCategory;

import static org.swpractice.util.Constants.LOCAL_ORIGIN;
import static org.swpractice.util.Constants.PRACTICE_SUB_CATEGORY_PATH;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_SUB_CATEGORY_PATH)
public interface PracticeSubCategoryRepository extends JpaRepository<PracticeSubCategory, Long> {
}

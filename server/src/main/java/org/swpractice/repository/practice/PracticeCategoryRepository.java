package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.PracticeCategory;

import static org.swpractice.util.Constants.PRACTICE_CATEGORY_PATH;

@CrossOrigin(origins = {"http://localhost:8081"}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_CATEGORY_PATH)
public interface PracticeCategoryRepository extends JpaRepository<PracticeCategory, Long> {
}

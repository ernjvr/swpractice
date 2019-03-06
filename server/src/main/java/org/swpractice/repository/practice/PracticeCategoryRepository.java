package org.swpractice.dao.practice;

import org.swpractice.model.practice.PracticeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RepositoryRestResource(path = "/practice-category")
@Transactional
public interface PracticeCategoryRepository extends JpaRepository<PracticeCategory, Long> {
    List<PracticeCategory> findByName(@Param("name") String name);
}

package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.Practice;

import java.util.List;

import static org.swpractice.util.Constants.PRACTICE_FIELD_NAME;
import static org.swpractice.util.Constants.PRACTICE_PATH;

@CrossOrigin(origins = {"http://localhost:8081"}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_PATH)
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    List<Practice> findByName(@Param(PRACTICE_FIELD_NAME) String name);
}

package org.swpractice.repository.practice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.practice.Practice;

import static org.swpractice.util.Constants.LOCAL_ORIGIN;
import static org.swpractice.util.Constants.PRACTICE_PATH;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = PRACTICE_PATH)
public interface PracticeRepository extends JpaRepository<Practice, Long> {
}

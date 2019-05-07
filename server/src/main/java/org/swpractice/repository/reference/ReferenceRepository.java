package org.swpractice.repository.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.reference.Reference;

import static org.swpractice.util.Constants.LOCAL_ORIGIN;
import static org.swpractice.util.Constants.REFERENCE_PATH;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = REFERENCE_PATH)
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}

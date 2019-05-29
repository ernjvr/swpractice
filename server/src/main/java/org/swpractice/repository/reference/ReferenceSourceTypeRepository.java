package org.swpractice.repository.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.reference.ReferenceSourceType;

import static org.swpractice.util.Constants.LOCAL_ORIGIN;
import static org.swpractice.util.Constants.REFERENCE_SOURCE_TYPE_PATH;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = REFERENCE_SOURCE_TYPE_PATH)
public interface ReferenceSourceTypeRepository extends JpaRepository<ReferenceSourceType, Long> {
}

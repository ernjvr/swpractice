package org.swpractice.repository.context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.context.ContextEntityType;

import static org.swpractice.util.Constants.CONTEXT_ENTITY_TYPE_PATH;
import static org.swpractice.util.Constants.LOCAL_ORIGIN;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = CONTEXT_ENTITY_TYPE_PATH)
public interface ContextEntityTypeRepository extends JpaRepository<ContextEntityType, Long> {
}

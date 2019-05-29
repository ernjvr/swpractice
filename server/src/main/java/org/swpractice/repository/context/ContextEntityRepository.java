package org.swpractice.repository.context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.swpractice.model.context.ContextEntity;

import static org.swpractice.util.Constants.*;

@CrossOrigin(origins = {LOCAL_ORIGIN}, maxAge = 3600)
@RepositoryRestResource(path = CONTEXT_ENTITY_PATH)
public interface ContextEntityRepository extends JpaRepository<ContextEntity, Long> {
}

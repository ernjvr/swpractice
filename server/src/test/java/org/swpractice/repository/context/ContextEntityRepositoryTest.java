package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextEntity;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ContextEntityRepositoryTest {

    @Autowired
    ContextEntityRepository repository;

    private ContextEntity contextEntity;

    @BeforeEach
    void reset() {
        var id = 9001L;
        final Optional<ContextEntity> contextEntityOptional = repository.findById(id);
        contextEntityOptional.ifPresentOrElse(entity -> contextEntity = entity,
                () -> fail("Expected Context Entity of id " + id));
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenContextEntityTypeIsNull() {
        contextEntity.setContextEntityType(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenCapabilityIsNull() {
        contextEntity.setCapability(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenMotivationIsNull() {
        contextEntity.setMotivation(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenEmpowermentIsNull() {
        contextEntity.setEmpowerment(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenCulturalCohesionIsNull() {
        contextEntity.setCulturalCohesion(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenPhysicalDistanceIsNull() {
        contextEntity.setPhysicalDistance(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenTemporalDistanceIsNull() {
        contextEntity.setTemporalDistance(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        assertThrows(DataIntegrityViolationException.class, () -> repository.delete(contextEntity));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextEntity.setId(null);
        ContextEntity saved = repository.save(contextEntity);
        assertDoesNotThrow(() -> repository.delete(saved));
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            repository.save(contextEntity);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
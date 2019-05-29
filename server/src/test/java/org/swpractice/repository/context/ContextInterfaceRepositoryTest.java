package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextInterface;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ContextInterfaceRepositoryTest {

    @Autowired
    ContextInterfaceRepository repository;

    private ContextInterface contextInterface;

    @BeforeEach
    void reset() {
        var id = 10001L;
        final Optional<ContextInterface> contextInterfaceOptional = repository.findById(id);
        contextInterfaceOptional.ifPresentOrElse(contextInterface -> this.contextInterface = contextInterface,
                () -> fail("Expected Context Interface of id " + id));
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenEntity1IsNull() {
        contextInterface.setEntity1(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenEntity2IsNull() {
        contextInterface.setEntity2(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenCulturalCohesionIsNull() {
        contextInterface.setCulturalCohesion(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenAvailabilityIsNull() {
        contextInterface.setAvailability(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenPhysicalDistanceIsNull() {
        contextInterface.setPhysicalDistance(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow nul values for required fields.
     */
    void dataIntegrityViolationExceptionThrownWhenTemporalDistanceIsNull() {
        contextInterface.setTemporalDistance(null);
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        // placeholder: feature not implemented yet
        //assertThrows(DataIntegrityViolationException.class, () -> repository.delete(contextInterface));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextInterface.setId(null);
        ContextInterface saved = repository.save(contextInterface);
        assertDoesNotThrow(() -> repository.delete(saved));
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            repository.save(contextInterface);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
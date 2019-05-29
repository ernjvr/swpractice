package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextValueDistance;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ContextValueDistanceRepositoryTest {

    @Autowired
    ContextValueDistanceRepository repository;

    private ContextValueDistance contextValueDistance = new ContextValueDistance();;

    @BeforeEach
    void reset() {
        contextValueDistance.setId(null);
        contextValueDistance.setValue(0);
        contextValueDistance.setDescription(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        Optional<ContextValueDistance> result = repository.findById(11001L);
        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(value -> repository.delete(value)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextValueDistance.setDescription("xxx");
        ContextValueDistance saved = repository.save(contextValueDistance);
        assertDoesNotThrow(() -> repository.delete(saved));
    }
    
    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveValueWithMaxSizeNotExceed() {
        contextValueDistance.setValue(2_147_483_647);
        contextValueDistance.setDescription("abc");
        assertDoesNotThrow(() -> repository.save(contextValueDistance));
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveValueWithMinSizeNotExceed() {
        contextValueDistance.setValue(-2_147_483_648);
        contextValueDistance.setDescription("abc");
        assertDoesNotThrow(() -> repository.save(contextValueDistance));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithLengthExceed() {
        contextValueDistance.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveDescriptionWithLengthNotExceed() {
        contextValueDistance.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> repository.save(contextValueDistance));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithEmptyValue() {
        contextValueDistance.setDescription("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithSpaceValues() {
        contextValueDistance.setDescription("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            repository.save(contextValueDistance);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
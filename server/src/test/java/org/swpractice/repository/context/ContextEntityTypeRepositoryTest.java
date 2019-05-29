package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextEntityType;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ContextEntityTypeRepositoryTest {

    @Autowired
    private ContextEntityTypeRepository repository;

    private ContextEntityType contextEntityType = new ContextEntityType();

    @BeforeEach
    void reset() {
        contextEntityType.setName(null);
        contextEntityType.setDescription(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        Optional<ContextEntityType> result = repository.findById(8001L);
        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(entityType ->
                repository.delete(entityType)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextEntityType.setName("xxx");
        ContextEntityType saved = repository.save(contextEntityType);
        assertDoesNotThrow(() -> repository.delete(saved));
    }

    @Test
    /**
     * Do not allow duplicate entries for unique columns.
     */
    void constraintViolationExceptionThrownWhenSaveDuplicateName() {
        contextEntityType.setName("architect");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow non-duplicate entries for unique columns.
     */
    void constraintViolationExceptionNotThrownWhenSaveNonDuplicateName() {
        contextEntityType.setName("xxx");
        assertDoesNotThrow(() -> repository.save(contextEntityType));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithLengthExceed() {
        contextEntityType.setName("12345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that does not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveNameWithLengthNotExceed() {
        contextEntityType.setName("12345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> repository.save(contextEntityType));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithLengthExceed() {
        contextEntityType.setName("yyy");
        contextEntityType.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveDescriptionWithLengthNotExceed() {
        contextEntityType.setName("zzz");
        contextEntityType.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> repository.save(contextEntityType));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithEmptyValue() {
        contextEntityType.setName("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithSpaceValues() {
        contextEntityType.setName("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            repository.save(contextEntityType);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
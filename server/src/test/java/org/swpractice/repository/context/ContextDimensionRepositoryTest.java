package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextDimension;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ContextDimensionRepositoryTest {

    @Autowired
    ContextDimensionRepository dimensionRepository;

    private ContextDimension contextDimension = new ContextDimension();;

    @BeforeEach
    void reset() {
        contextDimension.setId(null);
        contextDimension.setName(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        // placeholder: feature not implemented yet
        //Optional<ContextDimension> result = dimensionRepository.findById(4002L);
        //assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(dimension -> dimensionRepository.delete(dimension)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextDimension.setName("xxx");
        ContextDimension saved = dimensionRepository.save(contextDimension);
        assertDoesNotThrow(() -> dimensionRepository.delete(saved));
    }

    @Test
    /**
     * Do not allow duplicate entries for unique columns.
     */
    void constraintViolationExceptionThrownWhenSaveDuplicateName() {
        contextDimension.setName("people");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow non-duplicate entries for unique columns.
     */
    void constraintViolationExceptionNotThrownWhenSaveNonDuplicateName() {
        contextDimension.setName("yyy");
        assertDoesNotThrow(() -> dimensionRepository.save(contextDimension));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithLengthExceed() {
        contextDimension.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveNameWithLengthNotExceed() {
        contextDimension.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> dimensionRepository.save(contextDimension));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithEmptyValue() {
        contextDimension.setName("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithSpaceValues() {
        contextDimension.setName("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            dimensionRepository.save(contextDimension);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
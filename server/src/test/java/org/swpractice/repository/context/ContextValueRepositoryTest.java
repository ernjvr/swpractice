package org.swpractice.repository.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.context.ContextValue;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class ContextValueRepositoryTest {

    @Autowired
    ContextValueRepository valueRepository;

    private ContextValue contextValue = new ContextValue();;

    @BeforeEach
    void reset() {
        contextValue.setId(null);
        contextValue.setValue(0);
        contextValue.setDescription(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
//        Optional<ContextValue> result = valueRepository.findById(7001L);
//        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(value -> valueRepository.delete(value)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        contextValue.setDescription("xxx");
        ContextValue saved = valueRepository.save(contextValue);
        assertDoesNotThrow(() -> valueRepository.delete(saved));
    }
    
    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithLengthExceed() {
        contextValue.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveDescriptionWithLengthNotExceed() {
        contextValue.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> valueRepository.save(contextValue));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithEmptyValue() {
        contextValue.setDescription("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithSpaceValues() {
        contextValue.setDescription("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            valueRepository.save(contextValue);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
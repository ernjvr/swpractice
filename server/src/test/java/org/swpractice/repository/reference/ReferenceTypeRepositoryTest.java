package org.swpractice.repository.reference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.reference.ReferenceType;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class ReferenceTypeRepositoryTest {

    @Autowired
    ReferenceTypeRepository typeRepository;

    private ReferenceType referenceType = new ReferenceType();;

    @BeforeEach
    void reset() {
        referenceType.setId(null);
        referenceType.setName(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        Optional<ReferenceType> result = typeRepository.findById(5002L);
        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(type -> typeRepository.delete(type)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        referenceType.setName("xxx");
        ReferenceType saved = typeRepository.save(referenceType);
        assertDoesNotThrow(() -> typeRepository.delete(saved));
    }

    @Test
    /**
     * Do not allow duplicate entries for unique columns.
     */
    void constraintViolationExceptionThrownWhenSaveDuplicateName() {
        referenceType.setName("Book");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow non-duplicate entries for unique columns.
     */
    void constraintViolationExceptionNotThrownWhenSaveNonDuplicateName() {
        referenceType.setName("yyy");
        assertDoesNotThrow(() -> typeRepository.save(referenceType));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithLengthExceed() {
        referenceType.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveNameWithLengthNotExceed() {
        referenceType.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> typeRepository.save(referenceType));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithEmptyValue() {
        referenceType.setName("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithSpaceValues() {
        referenceType.setName("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            typeRepository.save(referenceType);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
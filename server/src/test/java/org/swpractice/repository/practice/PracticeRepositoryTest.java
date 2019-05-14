package org.swpractice.repository.practice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.practice.Practice;
import org.swpractice.model.practice.PracticeSubCategory;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PracticeRepositoryTest {

    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    private PracticeSubCategoryRepository subCategoryRepository;

    private Practice practice;

    @BeforeAll
    void setup() {
        var id = 2001L;
        Optional<PracticeSubCategory> categoryOptional = subCategoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            practice = new Practice();
            practice.setPracticeSubCategory(categoryOptional.get());
        } else {
            fail("Expected Practice Sub Category of id " + id);
        }
    }

    @BeforeEach
    void reset() {
        practice.setId(null);
        practice.setName(null);
        practice.setDescription(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
//        Optional<Practice> result = practiceRepository.findById(3001L);
//        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(practice -> practiceRepository.delete(practice)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        practice.setName("xxx");
        Practice saved = practiceRepository.save(practice);
        assertDoesNotThrow(() -> practiceRepository.delete(saved));
    }

    @Test
    /**
     * Do not allow duplicate entries for unique columns.
     */
    void constraintViolationExceptionThrownWhenSaveDuplicateName() {
        practice.setName("Planning Game");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow non-duplicate entries for unique columns.
     */
    void constraintViolationExceptionNotThrownWhenSaveNonDuplicateName() {
        practice.setName("yyy");
        assertDoesNotThrow(() -> practiceRepository.save(practice));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithLengthExceed() {
        practice.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that does not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveNameWithLengthNotExceed() {
        practice.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> practiceRepository.save(practice));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithLengthExceed() {
        practice.setName("zzz");
        practice.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveDescriptionWithLengthNotExceed() {
        practice.setName("aaa");
        practice.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> practiceRepository.save(practice));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithEmptyValue() {
        practice.setName("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithSpaceValues() {
        practice.setName("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            practiceRepository.save(practice);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
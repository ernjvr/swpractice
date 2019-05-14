package org.swpractice.repository.practice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.practice.PracticeCategory;
import org.swpractice.model.practice.PracticeSubCategory;

import java.util.Optional;

import static org.assertj.core.util.Throwables.getRootCause;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PracticeSubCategoryRepositoryTest {

    @Autowired
    private PracticeSubCategoryRepository subCategoryRepository;

    @Autowired
    private PracticeCategoryRepository categoryRepository;

    private PracticeSubCategory practiceSubCategory;

    @BeforeAll
    void setup() {
        var categoryId = 1001L;
        Optional<PracticeCategory> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            practiceSubCategory = new PracticeSubCategory();
            practiceSubCategory.setPracticeCategory(categoryOptional.get());
        } else {
            fail("Expected Practice Category of id " + categoryId);
        }
    }

    @BeforeEach
    void reset() {
        practiceSubCategory.setName(null);
        practiceSubCategory.setDescription(null);
    }

    @Test
    /**
     * Do not allow delete of record in parent table that will cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionThrownWhenDelete() {
        Optional<PracticeSubCategory> result = subCategoryRepository.findById(2001L);
        assertThrows(DataIntegrityViolationException.class, () -> result.ifPresent(subCategory -> subCategoryRepository.delete(subCategory)));
    }

    @Test
    /**
     * Allow delete of record in parent table that will NOT cause orphan records in child tables of the database.
     */
    void dataIntegrityViolationExceptionNotThrownWhenDelete() {
        Optional<PracticeSubCategory> result = subCategoryRepository.findById(2007L);
        assertDoesNotThrow(() -> result.ifPresent(subCategory -> subCategoryRepository.delete(subCategory)));
    }

    @Test
    /**
     * Do not allow duplicate entries for unique columns.
     */
    void constraintViolationExceptionThrownWhenSaveDuplicateName() {
        practiceSubCategory.setName("Roadmap");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow non-duplicate entries for unique columns.
     */
    void constraintViolationExceptionNotThrownWhenSaveNonDuplicateName() {
        practiceSubCategory.setName("xxx");
        assertDoesNotThrow(() -> subCategoryRepository.save(practiceSubCategory));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithLengthExceed() {
        practiceSubCategory.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that does not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveNameWithLengthNotExceed() {
        practiceSubCategory.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> subCategoryRepository.save(practiceSubCategory));
    }

    @Test
    /**
     * Do not allow entries that exceed maximum allowed length.
     */
    void constraintViolationExceptionThrownWhenSaveDescriptionWithLengthExceed() {
        practiceSubCategory.setName("yyy");
        practiceSubCategory.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890a");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Allow entries that do not exceed maximum allowed length.
     */
    void constraintViolationExceptionNotThrownWhenSaveDescriptionWithLengthNotExceed() {
        practiceSubCategory.setName("zzz");
        practiceSubCategory.setDescription("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        assertDoesNotThrow(() -> subCategoryRepository.save(practiceSubCategory));
    }

    @Test
    /**
     * Do not allow empty values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithEmptyValue() {
        practiceSubCategory.setName("");
        expectConstraintViolationExceptionOnSave();
    }

    @Test
    /**
     * Do not allow space-only values.
     */
    void constraintViolationExceptionThrownWhenSaveNameWithSpaceValues() {
        practiceSubCategory.setName("   ");
        expectConstraintViolationExceptionOnSave();
    }

    private void expectConstraintViolationExceptionOnSave() {
        try {
            subCategoryRepository.save(practiceSubCategory);
        } catch (Exception e) {
            assertEquals(javax.validation.ConstraintViolationException.class, getRootCause(e).getClass());
            return;
        }
        fail("Expected javax.validation.ConstraintViolationException");
    }
}
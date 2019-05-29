package org.swpractice.validation.service.reference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.swpractice.SWPracticeApplication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SWPracticeApplication.class)
class ReferenceSourceTypeValidationServiceImplTest {

    @Autowired
    ReferenceSourceTypeValidationServiceImpl service;

    @Test
    /**
     * True when the column value exists in the database.
     */
    void columnValueExistsExpectTrue() {
        boolean exists = service.columnValueExists("name", "book");
        assertTrue(exists);
    }

    @Test
    /**
     * False when the column value does not exist in the database.
     */
    void columnValueExistsExpectFalse() {
        boolean exists = service.columnValueExists("name", "xxx");
        assertFalse(exists);
    }
}
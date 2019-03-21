package org.swpractice.validation.service.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.swpractice.SWPracticeApplication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class PracticeValidationServiceImplTest {

    @Autowired
    PracticeValidationServiceImpl service;

    @Test
    public void columnValueExistsExpectTrue() {
        boolean exists = service.columnValueExists("name", "Planning Game");
        assertTrue(exists);
    }

    @Test
    public void columnValueExistsExpectFalse() {
        boolean exists = service.columnValueExists("name", "xxx");
        assertFalse(exists);
    }
}
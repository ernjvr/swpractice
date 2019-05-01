package org.swpractice.repository.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.practice.Practice;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class PracticeSubCategoryRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PracticeRepository repository;

    @Test
    public void findByIdPresent() {
        Optional<Practice> result = repository.findById(3001L);
        assertTrue(result.isPresent());
    }

    @Test
    public void findByIdNotPresent() {
        Optional<Practice> result = repository.findById(1001L);
        assertFalse(result.isPresent());
    }
}
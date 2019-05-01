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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class PracticeRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PracticeRepository repository;

    @Test
    public void findByName() {
        //List<Practice> byName = repository.findByName("Define");
        //logger.info("Practices by Name {}", byName);
    }
}
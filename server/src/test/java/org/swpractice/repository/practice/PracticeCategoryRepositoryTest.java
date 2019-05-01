package org.swpractice.repository.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.swpractice.SWPracticeApplication;
import org.swpractice.model.practice.PracticeCategory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SWPracticeApplication.class)
public class PracticeCategoryRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PracticeCategoryRepository repository;


    @Test
    public void sort() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        logger.info("Sorted PracticeCategories {}", repository.findAll(sort));
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<PracticeCategory> firstPage = repository.findAll(pageRequest);
        logger.info("First Page {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<PracticeCategory> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page {}", secondPage.getContent());
    }
}
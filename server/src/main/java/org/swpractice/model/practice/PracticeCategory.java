package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.practice.PracticeCategoryValidationService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static org.swpractice.util.Constants.*;


@Data
@Entity
@NamedQuery(name = PRACTICE_CATEGORY_FIND_BY_NAME, query = PRACTICE_CATEGORY_FIND_BY_NAME_QUERY)
public class PracticeCategory implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = PRACTICE_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE)
    @Size(min = 1, max = 50, message = PRACTICE_CATEGORY_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = PracticeCategoryValidationService.class,
            fieldName = PRACTICE_CATEGORY_FIELD_NAME,
            message = PRACTICE_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE)
    private String name;

    @OneToMany(mappedBy = PRACTICE_CATEGORY)
    private List<PracticeSubCategory> practiceSubCategory;

    @Override
    public Long getId() {
        return id;
    }
}
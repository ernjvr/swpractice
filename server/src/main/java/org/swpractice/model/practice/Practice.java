package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.practice.PracticeValidationService;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;


@Data
@Entity
@NamedQuery(name = PRACTICE_FIND_BY_NAME, query = PRACTICE_FIND_BY_NAME_QUERY)
public class Practice implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = PRACTICE_FIELD_NAME_REQUIRED_MESSAGE)
    @Size(min = 1, max = 100, message = PRACTICE_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = PracticeValidationService.class,
            fieldName = PRACTICE_FIELD_NAME,
            message = PRACTICE_FIELD_NAME_UNIQUE_MESSAGE)
    private String name;

    @Size(max = 500, message = PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @NotNull(message = PRACTICE_FIELD_PRACTICE_SUB_CATEGORY_REQUIRED_MESSAGE)
    @ManyToOne
    private PracticeSubCategory practiceSubCategory;

    @Override
    public Long getId() {
        return id;
    }
}

package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.practice.PracticeSubCategoryValidationService;
import org.swpractice.validation.service.practice.PracticeValidationService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static org.swpractice.util.Constants.*;


@Data
@Entity
@NamedQuery(name = PRACTICE_SUB_CATEGORY_FIND_BY_NAME, query = PRACTICE_SUB_CATEGORY_FIND_BY_NAME_QUERY)
public class PracticeSubCategory {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = PRACTICE_SUB_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE)
    @Size(min = 1, max = 100, message = PRACTICE_SUB_CATEGORY_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = PracticeSubCategoryValidationService.class,
            fieldName = PRACTICE_SUB_CATEGORY_FIELD_NAME,
            message = PRACTICE_SUB_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE)
    private String name;

    @Size(max = 500, message = PRACTICE_SUB_CATEGORY_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @NotNull(message = PRACTICE_SUB_CATEGORY_FIELD_PRACTICE_CATEGORY_REQUIRED_MESSAGE)
    @ManyToOne
    private PracticeCategory practiceCategory;

    @OneToMany(mappedBy = PRACTICE_SUB_CATEGORY)
    private List<Practice> practice;

    public Long getId() {
        return id;
    }
}

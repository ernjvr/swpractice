package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;
import org.swpractice.model.reference.Reference;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.practice.PracticeValidationService;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
@NamedQuery(name = PRACTICE_FIND_BY_NAME, query = PRACTICE_FIND_BY_NAME_QUERY)
public class Practice implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = PRACTICE_FIELD_NAME_REQUIRED_MESSAGE)
    @Strip
    @NotBlank
    @Size(min = 1, max = 100, message = PRACTICE_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = PracticeValidationService.class,
            fieldName = PRACTICE_FIELD_NAME,
            message = PRACTICE_FIELD_NAME_UNIQUE_MESSAGE)
    // index this column
    private String name;

    @Strip
    @Size(max = 500, message = PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @NotNull(message = PRACTICE_FIELD_PRACTICE_SUB_CATEGORY_REQUIRED_MESSAGE)
    @ManyToOne
    private PracticeSubCategory practiceSubCategory;

    @ManyToOne
    private Reference reference;

    @Override
    public Long getId() {
        return id;
    }
}

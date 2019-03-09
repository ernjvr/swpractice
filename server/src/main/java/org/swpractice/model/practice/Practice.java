package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.practice.PracticeValidationService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;


@Data
@Entity
@NamedQuery(name = PRACTICE_FIND_BY_NAME, query = PRACTICE_FIND_BY_NAME_QUERY)
public class Practice {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = PRACTICE_FIELD_NAME_REQUIRED_MESSAGE)
    @Size(min = 1, max = 50, message = PRACTICE_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = PracticeValidationService.class,
            fieldName = PRACTICE_FIELD_NAME,
            message = PRACTICE_FIELD_NAME_UNIQUE_MESSAGE)
    private String name;

    @Size(min = 1, max = 255, message = PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @ManyToOne
    private PracticeCategory practiceCategory;
}

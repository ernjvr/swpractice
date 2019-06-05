package org.swpractice.model.reference;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.reference.ReferenceSourceTypeValidationService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
@NamedQuery(name = REFERENCE_SOURCE_TYPE_FIND_BY_NAME, query = REFERENCE_SOURCE_TYPE_FIND_BY_NAME_QUERY)
public class ReferenceSourceType implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = REFERENCE_SOURCE_TYPE_FIELD_NAME_REQUIRED_MESSAGE)
    @Strip
    @NotBlank(message = REFERENCE_SOURCE_TYPE_FIELD_NAME_BLANK_MESSAGE)
    @Size(min = 1, max = 100, message = REFERENCE_SOURCE_TYPE_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = ReferenceSourceTypeValidationService.class,
            fieldName = REFERENCE_SOURCE_TYPE_FIELD_NAME,
            message = REFERENCE_SOURCE_TYPE_FIELD_NAME_UNIQUE_MESSAGE)
    // index this column
    private String name;

    @Override
    public Long getId() {
        return null;
    }
}

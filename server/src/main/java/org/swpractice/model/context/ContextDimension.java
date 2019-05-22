package org.swpractice.model.context;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.context.ContextDimensionValidationService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
@NamedQuery(name = CONTEXT_DIMENSION_FIND_BY_NAME, query = CONTEXT_DIMENSION_FIND_BY_NAME_QUERY)
public class ContextDimension implements Identifier {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull(message = CONTEXT_DIMENSION_FIELD_NAME_REQUIRED_MESSAGE)
    @Strip
    @NotBlank
    @Size(min = 1, max = 100, message = CONTEXT_DIMENSION_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = ContextDimensionValidationService.class,
            fieldName = CONTEXT_DIMENSION_FIELD_NAME,
            message = CONTEXT_DIMENSION_FIELD_NAME_UNIQUE_MESSAGE)
    // index this column
    private String name;

    @Override
    public Long getId() {
        return null;
    }
}

package org.swpractice.model.context;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;
import org.swpractice.validation.constraint.annotation.Unique;
import org.swpractice.validation.service.context.ContextEntityTypeValidationService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
@NamedQuery(name = CONTEXT_ENTITY_TYPE_FIND_BY_NAME, query = CONTEXT_ENTITY_TYPE_FIND_BY_NAME_QUERY)
public class ContextEntityType implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_ENTITY_TYPE_FIELD_NAME_REQUIRED_MESSAGE)
    @Strip
    @NotBlank(message = CONTEXT_ENTITY_TYPE_FIELD_NAME_BLANK_MESSAGE)
    @Size(min = 1, max = 50, message = CONTEXT_ENTITY_TYPE_FIELD_NAME_LENGTH_MESSAGE)
    @Column(unique = true)
    @Unique(service = ContextEntityTypeValidationService.class,
            fieldName = CONTEXT_ENTITY_TYPE_FIELD_NAME,
            message = CONTEXT_ENTITY_TYPE_FIELD_NAME_UNIQUE_MESSAGE)
    private String name;

    @Strip
    @Size(max = 500, message = CONTEXT_ENTITY_TYPE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @OneToMany(mappedBy = CONTEXT_ENTITY_TYPE)
    private List<ContextEntity> contextEntity;

    @Override
    public Long getId() {
        return id;
    }
}

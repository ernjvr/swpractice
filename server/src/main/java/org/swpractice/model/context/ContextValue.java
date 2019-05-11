package org.swpractice.model.context;

import lombok.Data;
import org.swpractice.model.Identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.swpractice.util.Constants.*;

@Data
@Entity
public class ContextValue implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_VALUE_FIELD_VALUE_REQUIRED_MESSAGE)
    // index this column
    private int value;

    @NotNull(message = CONTEXT_VALUE_FIELD_DESCRIPTION_REQUIRED_MESSAGE)
    @Size(min = 1, max = 500, message = CONTEXT_VALUE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @Override
    public Long getId() {
        return null;
    }
}

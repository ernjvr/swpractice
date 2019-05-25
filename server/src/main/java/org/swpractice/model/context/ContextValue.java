package org.swpractice.model.context;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
public class ContextValue implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_VALUE_FIELD_VALUE_REQUIRED_MESSAGE)
    @Min(value = -2_147_483_648, message = CONTEXT_VALUE_FIELD_YEAR_MIN_MESSAGE)
    @Max(value = 2_147_483_647, message = CONTEXT_VALUE_FIELD_YEAR_MAX_MESSAGE)
    private int value;

    @NotNull(message = CONTEXT_VALUE_FIELD_DESCRIPTION_REQUIRED_MESSAGE)
    @Strip
    @NotBlank
    @Size(min = 1, max = 500, message = CONTEXT_VALUE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @Override
    public Long getId() {
        return null;
    }
}

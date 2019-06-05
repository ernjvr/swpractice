package org.swpractice.model.context;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
public class ContextValueDistance implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_VALUE_DISTANCE_FIELD_VALUE_REQUIRED_MESSAGE)
    @Min(value = -2_147_483_648, message = CONTEXT_VALUE_DISTANCE_FIELD_VALUE_MIN_MESSAGE)
    @Max(value = 2_147_483_647, message = CONTEXT_VALUE_DISTANCE_FIELD_VALUE_MAX_MESSAGE)
    private int value;

    @NotNull(message = CONTEXT_VALUE_DISTANCE_FIELD_DESCRIPTION_REQUIRED_MESSAGE)
    @Strip
    @NotBlank(message = CONTEXT_VALUE_DISTANCE_FIELD_DESCRIPTION_BLANK_MESSAGE)
    @Size(min = 1, max = 500, message = CONTEXT_VALUE_DISTANCE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @OneToMany(mappedBy = CONTEXT_ENTITY_PHYSICAL_DISTANCE)
    private List<ContextEntity> contextEntityPhysicalDistance;

    @OneToMany(mappedBy = CONTEXT_ENTITY_TEMPORAL_DISTANCE)
    private List<ContextEntity> contextEntityTemporalDistance;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_PHYSICAL_DISTANCE)
    private List<ContextInterface> contextInterfacePhysicalDistance;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_TEMPORAL_DISTANCE)
    private List<ContextInterface> contextInterfaceTemporalDistance;

    @Override
    public Long getId() {
        return null;
    }
}

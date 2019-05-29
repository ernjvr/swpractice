package org.swpractice.model.context;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ContextValueLevel implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_VALUE_LEVEL_FIELD_VALUE_REQUIRED_MESSAGE)
    @Min(value = -2_147_483_648, message = CONTEXT_VALUE_LEVEL_FIELD_VALUE_MIN_MESSAGE)
    @Max(value = 2_147_483_647, message = CONTEXT_VALUE_LEVEL_FIELD_VALUE_MAX_MESSAGE)
    private int value;

    @NotNull(message = CONTEXT_VALUE_LEVEL_FIELD_DESCRIPTION_REQUIRED_MESSAGE)
    @Strip
    @NotBlank
    @Size(min = 1, max = 500, message = CONTEXT_VALUE_LEVEL_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @OneToMany(mappedBy = CONTEXT_ENTITY_CAPABILITY)
    @JsonManagedReference(value = CONTEXT_ENTITY_CAPABILITY)
    private List<ContextEntity> contextEntityCapability;

    @OneToMany(mappedBy = CONTEXT_ENTITY_MOTIVATION)
    @JsonManagedReference(value = CONTEXT_ENTITY_MOTIVATION)
    private List<ContextEntity> contextEntityMotivation;

    @OneToMany(mappedBy = CONTEXT_ENTITY_EMPOWERMENT)
    @JsonManagedReference(value = CONTEXT_ENTITY_EMPOWERMENT)
    private List<ContextEntity> contextEntityEmpowerment;

    @OneToMany(mappedBy = CONTEXT_ENTITY_CULTURAL_COHESION)
    @JsonManagedReference(value = CONTEXT_ENTITY_CULTURAL_COHESION)
    private List<ContextEntity> contextEntityCulturalCohesion;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_CULTURAL_COHESION)
    @JsonManagedReference(value = CONTEXT_INTERFACE_CULTURAL_COHESION)
    private List<ContextInterface> contextInterfaceCulturalCohesion;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_AVAILABILITY)
    @JsonManagedReference(value = CONTEXT_INTERFACE_AVAILABILITY)
    private List<ContextInterface> contextInterfaceAvailability;

    @Override
    public Long getId() {
        return null;
    }
}

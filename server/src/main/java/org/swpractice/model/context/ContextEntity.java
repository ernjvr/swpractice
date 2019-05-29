package org.swpractice.model.context;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.listener.StripListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
public class ContextEntity implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_ENTITY_FIELD_CONTEXT_ENTITY_CATEGORY_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_TYPE)
    @JsonBackReference(value = CONTEXT_ENTITY_TYPE)
    private ContextEntityType contextEntityType;

    @NotNull(message = CONTEXT_ENTITY_FIELD_CAPABILITY_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_CAPABILITY)
    @JsonBackReference(value = CONTEXT_ENTITY_CAPABILITY)
    private ContextValueLevel capability;

    @NotNull(message = CONTEXT_ENTITY_FIELD_MOTIVATION_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_MOTIVATION)
    @JsonBackReference(value = CONTEXT_ENTITY_MOTIVATION)
    private ContextValueLevel motivation;

    @NotNull(message = CONTEXT_ENTITY_FIELD_EMPOWERMENT_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_EMPOWERMENT)
    @JsonBackReference(value = CONTEXT_ENTITY_EMPOWERMENT)
    private ContextValueLevel empowerment;

    @NotNull(message = CONTEXT_ENTITY_FIELD_CULTURAL_COHESION_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_CULTURAL_COHESION)
    @JsonBackReference(value = CONTEXT_ENTITY_CULTURAL_COHESION)
    private ContextValueLevel culturalCohesion;

    @NotNull(message = CONTEXT_ENTITY_FIELD_PHYSICAL_DISTANCE_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_PHYSICAL_DISTANCE)
    @JsonBackReference(value = CONTEXT_ENTITY_PHYSICAL_DISTANCE)
    private ContextValueDistance physicalDistance;

    @NotNull(message = CONTEXT_ENTITY_FIELD_TEMPORAL_DISTANCE_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_ENTITY_TEMPORAL_DISTANCE)
    @JsonBackReference(value = CONTEXT_ENTITY_TEMPORAL_DISTANCE)
    private ContextValueDistance temporalDistance;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_ENTITY1)
    @JsonManagedReference(value = CONTEXT_INTERFACE_ENTITY1)
    private List<ContextInterface> contextInterface1;

    @OneToMany(mappedBy = CONTEXT_INTERFACE_ENTITY1)
    @JsonManagedReference(value = CONTEXT_INTERFACE_ENTITY1)
    private List<ContextInterface> contextInterface2;

    @Override
    public Long getId() {
        return id;
    }
}

package org.swpractice.model.context;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.listener.StripListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
public class ContextInterface implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_CONTEXT_ENTITY1_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_ENTITY1)
    @JsonBackReference(value = CONTEXT_INTERFACE_ENTITY1)
    private ContextEntity entity1;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_CONTEXT_ENTITY2_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_ENTITY2)
    @JsonBackReference(value = CONTEXT_INTERFACE_ENTITY2)
    private ContextEntity entity2;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_CULTURAL_COHESION_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_CULTURAL_COHESION)
    @JsonBackReference(value = CONTEXT_INTERFACE_CULTURAL_COHESION)
    private ContextValueLevel culturalCohesion;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_AVAILABILITY_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_AVAILABILITY)
    @JsonBackReference(value = CONTEXT_INTERFACE_AVAILABILITY)
    private ContextValueLevel availability;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_PHYSICAL_DISTANCE_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_PHYSICAL_DISTANCE)
    @JsonBackReference(value = CONTEXT_INTERFACE_PHYSICAL_DISTANCE)
    private ContextValueDistance physicalDistance;

    @NotNull(message = CONTEXT_INTERFACE_FIELD_TEMPORAL_DISTANCE_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = CONTEXT_INTERFACE_TEMPORAL_DISTANCE)
    @JsonBackReference(value = CONTEXT_INTERFACE_TEMPORAL_DISTANCE)
    private ContextValueDistance temporalDistance;

    @Override
    public Long getId() {
        return id;
    }
}

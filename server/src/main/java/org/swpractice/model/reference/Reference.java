package org.swpractice.model.reference;

import lombok.Data;
import org.swpractice.model.Identifier;
import org.swpractice.model.annotation.Strip;
import org.swpractice.model.annotation.listener.StripListener;
import org.swpractice.model.practice.Practice;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

import static org.swpractice.util.Constants.*;

@Data
@Entity
@EntityListeners(StripListener.class)
public class Reference implements Identifier {

    @Id
    @GeneratedValue
    private Long id;

    @Strip
    @Size(max = 100, message = REFERENCE_FIELD_AUTHOR_LENGTH_MESSAGE)
    private String author;

    @Column(nullable = true)
    @Min(value = 0, message = REFERENCE_FIELD_YEAR_MIN_MESSAGE)
    @Max(value = 9999, message = REFERENCE_FIELD_YEAR_MAX_MESSAGE)
    private int year;

    @NotNull(message = REFERENCE_FIELD_REFERENCE_REQUIRED_MESSAGE)
    @Strip
    @NotBlank
    @Size(max = 2500, message = REFERENCE_FIELD_DESCRIPTION_LENGTH_MESSAGE)
    private String reference;

    @NotNull(message = REFERENCE_FIELD_REFERENCE_TYPE_REQUIRED_MESSAGE)
    @ManyToOne
    @JoinColumn(name = REFERENCE_SOURCE_TYPE)
    private ReferenceSourceType referenceSourceType;

    @OneToMany(mappedBy = REFERENCE)
    private List<Practice> practice;

    @Override
    public Long getId() {
        return id;
    }
}
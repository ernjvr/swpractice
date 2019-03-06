package org.swpractice.model.practice;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Practice {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long practiceCategoryId;
}

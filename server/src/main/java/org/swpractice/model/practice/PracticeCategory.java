package org.swpractice.model.practice;

import lombok.Data;
import org.swpractice.validation.constraints.annotations.Unique;
import org.swpractice.validation.practice.PracticeCategoryValidatorService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class PracticeCategory {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true)
    @Unique(service = PracticeCategoryValidatorService.class, fieldName = "name",
            message = "{unique.practice.category.name}")
    private String name;
}
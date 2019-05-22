package org.swpractice.model.annotation.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swpractice.model.annotation.Strip;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * StripListener listens for events on Entity classes annotated with @EntityListeners(StripListener.class)
 * to strip whitespace from String fields annotated with the @Strip annotation.
 */
public class StripListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<Class<?>, Set<Field>> stripProperties = new HashMap<>();

    /**
     * Strip the String fields before persisting the entity to the database.
     * @param entity The entity object to be persisted.
     */
    @PrePersist
    @PreUpdate
    public void stripBeforeSave(final Object entity) {
        getStripPropertiesFrom(entity.getClass()).forEach(field -> stripFieldValue(entity, field));
    }

    /**
     * Get all the String fields annotated with the Strip class.
     * @param entityClass The entity class to be persisted.
     * @return The String fields to be stripped.
     */
    private Set<Field> getStripPropertiesFrom(Class<?> entityClass) {

        if (Object.class.equals(entityClass)) {
            return Collections.emptySet();
        }
        Set<Field> propertiesToStrip = stripProperties.get(entityClass);

        if (propertiesToStrip == null) {
            propertiesToStrip = Arrays.stream(entityClass.getDeclaredFields())
                    .filter(field -> field.getType().equals(String.class) && field.getAnnotation(Strip.class) != null)
                    .peek(field -> field.setAccessible(true))
                    .collect(Collectors.toSet());
            stripProperties.put(entityClass, propertiesToStrip);
        }
        return propertiesToStrip;
    }

    /**
     * Strip white space from the field value.
     * @param entity The entity to be persisted.
     * @param field The field to strip.
     */
    private void stripFieldValue(final Object entity, Field field) {
        try {
            final String value = (String) field.get(entity);

            if (value != null) {
                field.set(entity, value.strip());
            }
        } catch (IllegalAccessException e) {
            logger.debug(String.format("stripFieldValue: %s", e.getMessage()));
        }
    }
}

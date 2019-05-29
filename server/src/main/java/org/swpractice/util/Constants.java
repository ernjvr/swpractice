package org.swpractice.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Constants {

    public static final String API_ROOT_PATTERN = "/api/**";
    public static final String LOCAL_ORIGIN = "http://localhost:8081";
    public static final String PATCH = "PATCH";

    public static final String CONTACT_NAME = "Diana Kirk";
    public static final String CONTACT_URL = "http://www.spe.com";
    public static final String CONTACT_EMAIL = "dianakirk@gmail.com";

    public static final String API_INFO_TITLE = "Software Practices Evidence API Documentation";
    public static final String API_INFO_DESCRIPTION = "Many software development methodologies have been proposed for the development of computer software. These include the more traditional models, for example, Waterfall and Spiral, and the more recent agile approaches, for example, XP and Scrum. An original belief common to all was that the selected approach must be followed exactly to achieve the benefits claimed. It is now generally accepted that practitioners do not follow any methodology to the letter, but instead tailor according to the project's environment. But there are dangers inherent in ad-hoc tailoring. How do we know whether implementing or tailoring a practice will be effective, or will cause unexpected issues? The vision for this application is to provide support for practitioners who want to know which practices might be effective for their project and which to avoid. This represents a long term goal - we must first build and grow an evidence repository linking specific software practices with elements of context. As a new initiative, this application at present is not yet ready for decision support. Rather, the application provides a vehicle for evidence accumulation on situated software practices.";
    public static final String API_INFO_VERSION = "1.0";
    public static final String API_INFO_TOS = "urn:tos";
    public static final String API_INFO_LICENCE = "Apache 2.0";
    public static final String API_INFO_LICENCE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    public static final Set<String> API_DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Collections.singletonList("application/json"));

    public static final String FIELD_NAME_NOT_SUPPORTED = "Field name '%s' not supported.";

    public static final String CONTEXT_DIMENSION_FIND_BY_NAME = "ContextDimension.find_by_name";
    public static final String CONTEXT_DIMENSION_FIND_BY_NAME_QUERY = "select d from ContextDimension d where lower(trim(d.name)) = lower(trim(:name))";
    public static final String CONTEXT_DIMENSION_FIELD_NAME_UNIQUE_MESSAGE = "{context.dimension.name.unique}";
    public static final String CONTEXT_DIMENSION_FIELD_NAME_REQUIRED_MESSAGE = "{context.dimension.name.required}";
    public static final String CONTEXT_DIMENSION_FIELD_NAME_LENGTH_MESSAGE = "{context.dimension.name.length}";
    public static final String CONTEXT_DIMENSION_PATH = "/context-dimensions";
    public static final String CONTEXT_DIMENSION_FIELD_NAME = "name";


    public static final String CONTEXT_ENTITY_FIELD_CONTEXT_ENTITY_CATEGORY_REQUIRED_MESSAGE = "{context.entity.context-entity-category.required}";
    public static final String CONTEXT_ENTITY_FIELD_CAPABILITY_REQUIRED_MESSAGE = "{context.entity.capability.required}";
    public static final String CONTEXT_ENTITY_FIELD_MOTIVATION_REQUIRED_MESSAGE = "{context.entity.motivation.required}";
    public static final String CONTEXT_ENTITY_FIELD_EMPOWERMENT_REQUIRED_MESSAGE = "{context.entity.empowerment.required}";
    public static final String CONTEXT_ENTITY_FIELD_CULTURAL_COHESION_REQUIRED_MESSAGE = "{context.entity.cultural.cohesion.required}";
    public static final String CONTEXT_ENTITY_FIELD_PHYSICAL_DISTANCE_REQUIRED_MESSAGE = "{context.entity.physical.distance.required}";
    public static final String CONTEXT_ENTITY_FIELD_TEMPORAL_DISTANCE_REQUIRED_MESSAGE = "{context.entity.temporal.distance.required}";

    public static final String CONTEXT_ENTITY_TYPE = "contextEntityType";
    public static final String CONTEXT_ENTITY_CAPABILITY = "capability";
    public static final String CONTEXT_ENTITY_MOTIVATION = "motivation";
    public static final String CONTEXT_ENTITY_EMPOWERMENT = "empowerment";
    public static final String CONTEXT_ENTITY_CULTURAL_COHESION = "culturalCohesion";
    public static final String CONTEXT_ENTITY_PHYSICAL_DISTANCE = "physicalDistance";
    public static final String CONTEXT_ENTITY_TEMPORAL_DISTANCE = "temporalDistance";
    public static final String CONTEXT_ENTITY_TYPE_FIND_BY_NAME = "ContextEntityType.find_by_name";
    public static final String CONTEXT_ENTITY_TYPE_FIND_BY_NAME_QUERY = "select c from ContextEntityType c where lower(trim(c.name)) = lower(trim(:name))";
    public static final String CONTEXT_ENTITY_TYPE_FIELD_NAME_UNIQUE_MESSAGE = "{context.entity.category.name.unique}";
    public static final String CONTEXT_ENTITY_TYPE_FIELD_NAME_REQUIRED_MESSAGE = "{context.entity.category.name.required}";
    public static final String CONTEXT_ENTITY_TYPE_FIELD_NAME_LENGTH_MESSAGE = "{context.entity.category.name.length}";
    public static final String CONTEXT_ENTITY_TYPE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{context.entity.category.description.length}";
    public static final String CONTEXT_ENTITY_TYPE_PATH = "/context-entity-types";
    public static final String CONTEXT_ENTITY_TYPE_FIELD_NAME = "name";

    public static final String CONTEXT_ENTITY_PATH = "/context-entities";
    public static final String CONTEXT_INTERFACE_PATH = "/context-interfaces";

    public static final String CONTEXT_INTERFACE_ENTITY1 = "entity1";
    public static final String CONTEXT_INTERFACE_ENTITY2 = "entity2";
    public static final String CONTEXT_INTERFACE_CULTURAL_COHESION = "culturalCohesion";
    public static final String CONTEXT_INTERFACE_AVAILABILITY = "availability";
    public static final String CONTEXT_INTERFACE_PHYSICAL_DISTANCE = "physicalDistance";
    public static final String CONTEXT_INTERFACE_TEMPORAL_DISTANCE = "temporalDistance";
    public static final String CONTEXT_INTERFACE_FIELD_CONTEXT_ENTITY1_REQUIRED_MESSAGE = "{context.interface.context-entity1.required}";
    public static final String CONTEXT_INTERFACE_FIELD_CONTEXT_ENTITY2_REQUIRED_MESSAGE = "{context.interface.context-entity2.required}";
    public static final String CONTEXT_INTERFACE_FIELD_CULTURAL_COHESION_REQUIRED_MESSAGE = "{context.interface.cultural.cohesion.required}";
    public static final String CONTEXT_INTERFACE_FIELD_AVAILABILITY_REQUIRED_MESSAGE = "{context.interface.availability.required}";
    public static final String CONTEXT_INTERFACE_FIELD_PHYSICAL_DISTANCE_REQUIRED_MESSAGE = "{context.interface.physical.distance.required}";
    public static final String CONTEXT_INTERFACE_FIELD_TEMPORAL_DISTANCE_REQUIRED_MESSAGE = "{context.interface.temporal.distance.required}";

    public static final String CONTEXT_VALUE_DISTANCE_FIELD_VALUE_REQUIRED_MESSAGE = "{context.value.distance.value.required}";
    public static final String CONTEXT_VALUE_DISTANCE_FIELD_DESCRIPTION_REQUIRED_MESSAGE = "{context.value.distance.description.required}";
    public static final String CONTEXT_VALUE_DISTANCE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{context.value.distance.description.length}";
    public static final String CONTEXT_VALUE_DISTANCE_FIELD_VALUE_MIN_MESSAGE = "{context.value.distance.value.min}";
    public static final String CONTEXT_VALUE_DISTANCE_FIELD_VALUE_MAX_MESSAGE = "{context.value.distance.value.max}";
    public static final String CONTEXT_VALUE_DISTANCE_PATH = "/context-value-distances";

    public static final String CONTEXT_VALUE_LEVEL_FIELD_VALUE_REQUIRED_MESSAGE = "{context.value.level.value.required}";
    public static final String CONTEXT_VALUE_LEVEL_FIELD_DESCRIPTION_REQUIRED_MESSAGE = "{context.value.level.description.required}";
    public static final String CONTEXT_VALUE_LEVEL_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{context.value.level.description.length}";
    public static final String CONTEXT_VALUE_LEVEL_FIELD_VALUE_MIN_MESSAGE = "{context.value.level.value.min}";
    public static final String CONTEXT_VALUE_LEVEL_FIELD_VALUE_MAX_MESSAGE = "{context.value.level.value.max}";
    public static final String CONTEXT_VALUE_LEVEL_PATH = "/context-value-levels";

    public static final String PRACTICE_FIND_BY_NAME = "Practice.find_by_name";
    public static final String PRACTICE_FIND_BY_NAME_QUERY = "select p from Practice p where lower(trim(p.name)) = lower(trim(:name))";
    public static final String PRACTICE_FIELD_NAME_UNIQUE_MESSAGE = "{practice.name.unique}";
    public static final String PRACTICE_FIELD_NAME_REQUIRED_MESSAGE = "{practice.name.required}";
    public static final String PRACTICE_FIELD_PRACTICE_SUB_CATEGORY_REQUIRED_MESSAGE = "{practice.practice-sub-category.required}";
    public static final String PRACTICE_FIELD_NAME_LENGTH_MESSAGE = "{practice.name.length}";
    public static final String PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{practice.description.length}";
    public static final String PRACTICE_PATH = "/practices";
    public static final String PRACTICE_FIELD_NAME = "name";

    public static final String PRACTICE_CATEGORY = "practiceCategory";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME = "PracticeCategory.find_by_name";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeCategory p where lower(trim(p.name)) = lower(trim(:name))";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.category.name.unique}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.category.name.required}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.category.name.length}";
    public static final String PRACTICE_CATEGORY_PATH = "/practice-categories";
    public static final String PRACTICE_CATEGORY_FIELD_NAME = "name";

    public static final String PRACTICE_SUB_CATEGORY = "practiceSubCategory";
    public static final String PRACTICE_SUB_CATEGORY_FIND_BY_NAME = "PracticeSubCategory.find_by_name";
    public static final String PRACTICE_SUB_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeSubCategory p where lower(trim(p.name)) = lower(trim(:name))";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.sub.category.name.unique}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.sub.category.name.required}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_PRACTICE_CATEGORY_REQUIRED_MESSAGE = "{practice.sub.category.practice-category.required}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.sub.category.name.length}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{practice.sub.category.description.length}";
    public static final String PRACTICE_SUB_CATEGORY_PATH = "/practice-sub-categories";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME = "name";

    public static final String REFERENCE = "reference";
    public static final String REFERENCE_FIELD_REFERENCE_TYPE_REQUIRED_MESSAGE = "{reference.reference-type.required}";
    public static final String REFERENCE_FIELD_REFERENCE_REQUIRED_MESSAGE = "{reference.reference.required}";
    public static final String REFERENCE_FIELD_AUTHOR_LENGTH_MESSAGE = "{reference.author.length}";
    public static final String REFERENCE_FIELD_YEAR_MIN_MESSAGE = "{reference.year.min}";
    public static final String REFERENCE_FIELD_YEAR_MAX_MESSAGE = "{reference.year.max}";
    public static final String REFERENCE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{reference.description.length}";
    public static final String REFERENCE_PATH = "/references";

    public static final String REFERENCE_SOURCE_TYPE = "referenceSourceType";
    public static final String REFERENCE_SOURCE_TYPE_FIND_BY_NAME = "ReferenceSourceType.find_by_name";
    public static final String REFERENCE_SOURCE_TYPE_FIND_BY_NAME_QUERY = "select t from ReferenceSourceType t where lower(trim(t.name)) = lower(trim(:name))";
    public static final String REFERENCE_SOURCE_TYPE_FIELD_NAME_UNIQUE_MESSAGE = "{reference.source.type.name.unique}";
    public static final String REFERENCE_SOURCE_TYPE_FIELD_NAME_REQUIRED_MESSAGE = "{reference.source.type.name.required}";
    public static final String REFERENCE_SOURCE_TYPE_FIELD_NAME_LENGTH_MESSAGE = "{reference.source.type.name.length}";
    public static final String REFERENCE_SOURCE_TYPE_PATH = "/reference-source-types";
    public static final String REFERENCE_SOURCE_TYPE_FIELD_NAME = "name";
}
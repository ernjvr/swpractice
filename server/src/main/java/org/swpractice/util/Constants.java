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

    public static final String CONTEXT_VALUE_FIELD_VALUE_REQUIRED_MESSAGE = "{context.value.value.required}";
    public static final String CONTEXT_VALUE_FIELD_DESCRIPTION_REQUIRED_MESSAGE = "{context.value.description.required}";
    public static final String CONTEXT_VALUE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{context.value.description.length}";
    public static final String CONTEXT_VALUE_PATH = "/context-values";

    public static final String REFERENCE_TYPE_FIND_BY_NAME = "ReferenceType.find_by_name";
    public static final String REFERENCE_TYPE_FIND_BY_NAME_QUERY = "select t from ReferenceType t where lower(trim(t.name)) = lower(trim(:name))";
    public static final String REFERENCE_TYPE_FIELD_NAME_UNIQUE_MESSAGE = "{reference.type.name.unique}";
    public static final String REFERENCE_TYPE_FIELD_NAME_REQUIRED_MESSAGE = "{reference.type.name.required}";
    public static final String REFERENCE_TYPE_FIELD_NAME_LENGTH_MESSAGE = "{reference.type.name.length}";
    public static final String REFERENCE_TYPE_PATH = "/reference-types";
    public static final String REFERENCE_TYPE_FIELD_NAME = "name";

    public static final String PRACTICE_CATEGORY = "practiceCategory";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME = "PracticeCategory.find_by_name";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeCategory p where lower(trim(p.name)) = lower(trim(:name))";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.category.name.unique}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.category.name.required}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.category.name.length}";
    public static final String PRACTICE_CATEGORY_PATH = "/practice-categories";
    public static final String PRACTICE_CATEGORY_FIELD_NAME = "name";

    public static final String PRACTICE_FIND_BY_NAME = "Practice.find_by_name";
    public static final String PRACTICE_FIND_BY_NAME_QUERY = "select p from Practice p where lower(trim(p.name)) = lower(trim(:name))";
    public static final String PRACTICE_FIELD_NAME_UNIQUE_MESSAGE = "{practice.name.unique}";
    public static final String PRACTICE_FIELD_NAME_REQUIRED_MESSAGE = "{practice.name.required}";
    public static final String PRACTICE_FIELD_PRACTICE_SUB_CATEGORY_REQUIRED_MESSAGE = "{practice.practice-sub-category.required}";
    public static final String PRACTICE_FIELD_NAME_LENGTH_MESSAGE = "{practice.name.length}";
    public static final String PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{practice.description.length}";
    public static final String PRACTICE_PATH = "/practices";
    public static final String PRACTICE_FIELD_NAME = "name";

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
}
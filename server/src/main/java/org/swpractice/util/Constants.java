package org.swpractice.util;

public class Constants {

    public static final String API_ROOT_PATTERN = "/api/**";
    public static final String LOCAL_ORIGIN = "http://localhost:8081";
    public static final String PATCH = "PATCH";

    public static final String PRACTICE_CATEGORY = "practiceCategory";
    public static final String PRACTICE_SUB_CATEGORY = "practiceSubCategory";
    public static final String FIELD_NAME_NOT_SUPPORTED = "Field name '%s' not supported.";

    public static final String PRACTICE_CATEGORY_FIND_BY_NAME = "PracticeCategory.find_by_name";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeCategory p where lower(p.name) = lower(:name)";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.category.name.unique}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.category.name.required}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.category.name.length}";
    public static final String PRACTICE_CATEGORY_PATH = "/practice-category";
    public static final String PRACTICE_CATEGORY_FIELD_NAME = "name";

    public static final String PRACTICE_FIND_BY_NAME = "Practice.find_by_name";
    public static final String PRACTICE_FIND_BY_NAME_QUERY = "select p from Practice p where lower(p.name) = lower(:name)";
    public static final String PRACTICE_FIELD_NAME_UNIQUE_MESSAGE = "{practice.name.unique}";
    public static final String PRACTICE_FIELD_NAME_REQUIRED_MESSAGE = "{practice.name.required}";
    public static final String PRACTICE_FIELD_PRACTICE_SUB_CATEGORY_REQUIRED_MESSAGE = "{practice.practice-sub-category.required}";
    public static final String PRACTICE_FIELD_NAME_LENGTH_MESSAGE = "{practice.name.length}";
    public static final String PRACTICE_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{practice.description.length}";
    public static final String PRACTICE_PATH = "/practice";
    public static final String PRACTICE_FIELD_NAME = "name";

    public static final String PRACTICE_SUB_CATEGORY_FIND_BY_NAME = "PracticeSubCategory.find_by_name";
    public static final String PRACTICE_SUB_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeSubCategory p where lower(p.name) = lower(:name)";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.sub.category.name.unique}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.sub.category.name.required}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_PRACTICE_CATEGORY_REQUIRED_MESSAGE = "{practice.sub.category.practice-category.required}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.sub.category.name.length}";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_DESCRIPTION_LENGTH_MESSAGE = "{practice.sub.category.description.length}";
    public static final String PRACTICE_SUB_CATEGORY_PATH = "/practice-sub-category";
    public static final String PRACTICE_SUB_CATEGORY_FIELD_NAME = "name";
}

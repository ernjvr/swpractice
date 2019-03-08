package org.swpractice.util;

public class Constants {

    public static final String PRACTICE_CATEGORY_FIND_BY_NAME = "PracticeCategory.find_by_name";
    public static final String PRACTICE_CATEGORY_FIND_BY_NAME_QUERY = "select p from PracticeCategory p where p.name = :name";
    public static final String PRACTICE_CATEGORY_FIELD_NAME = "name";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_UNIQUE_MESSAGE = "{practice.category.name.unique}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_REQUIRED_MESSAGE = "{practice.category.name.required}";
    public static final String PRACTICE_CATEGORY_FIELD_NAME_LENGTH_MESSAGE = "{practice.category.name.length}";
    public static final String PRACTICE_CATEGORY_PATH = "/practice-category";
}

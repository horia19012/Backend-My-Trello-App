package com.example.TaskManagement.enums;

/**
 * Enumeration representing various types of modifications that can occur in a project.
 */
public enum ProjectModification {
    /**
     * Indicates that the deadline of the project has been reached.
     */
    PROJECT_DEADLINE_REACHED,

    /**
     * Indicates that the deadline of the project has been changed.
     */
    PROJECT_DEADLINE_CHANGED,

    /**
     * Indicates that the description of the project has been modified.
     */
    PROJECT_DESCRIPTION_MODIFICATION,

    /**
     * Indicates that the owner of the project has been changed.
     */
    PROJECT_OWNER_CHANGED,

    /**
     * Indicates that a task has been added to the project.
     */
    PROJECT_ADDED_TASK
}


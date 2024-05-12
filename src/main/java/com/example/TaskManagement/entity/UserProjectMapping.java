package com.example.TaskManagement.entity;

import jakarta.persistence.*;


/**
 * Entity representing the mapping between a user and a project.
 * This class facilitates the association between users and projects, enabling the tracking of which users are assigned to specific projects.
 */
@Entity
@Table(name = "userprojectmappings")
public class UserProjectMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "project_id", nullable = false)
    private int projectId;

    /**
     * Constructs a new UserProjectMapping with specified user ID and project ID.
     *
     * @param userId    the ID of the user involved in the project.
     * @param projectId the ID of the project to which the user is mapped.
     */
    public UserProjectMapping(int userId, int projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    /**
     * Default constructor required for JPA.
     */
    public UserProjectMapping() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Gets the user ID associated with this mapping.
     *
     * @return the ID of the user.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for this mapping.
     *
     * @param userId the new user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the project ID associated with this mapping.
     *
     * @return the ID of the project.
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID for this mapping.
     *
     * @param projectId the new project ID to set.
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the unique identifier for this mapping.
     *
     * @return the mapping's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this mapping.
     *
     * @param id the new identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }
}
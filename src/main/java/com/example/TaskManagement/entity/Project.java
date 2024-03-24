package com.example.TaskManagement.entity;

import jakarta.persistence.*;

/**
 * Entity class representing a project in the system.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    /**
     * Default constructor for the Project class.
     */
    public Project() {
    }

    /**
     * Getter method for the project ID.
     * @return The project ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for the project ID.
     * @param id The project ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for the project name.
     * @return The project name.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Setter method for the project name.
     * @param projectName The project name to set.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Getter method for the project description.
     * @return The project description.
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Setter method for the project description.
     * @param projectDescription The project description to set.
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}

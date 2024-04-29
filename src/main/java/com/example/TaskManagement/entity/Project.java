package com.example.TaskManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity class representing a project.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    /**
     * Default constructor.
     */
    public Project() {
    }

    /**
     * Parameterized constructor.
     *
     * @param projectName        The name of the project.
     * @param projectDescription The description of the project.
     * @param deadline           The deadline of the project.
     * @param status             The status of the project.
     * @param owner              The owner of the project.
     */
    public Project(String projectName, String projectDescription, Date deadline, String status, User owner) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.deadline = deadline;
        this.status = status;
        this.owner = owner;
    }

    public Project(String projectName, String projectDescription, User owner) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.owner = owner;
    }

    public Project(String projectName,String projectDescription){
        this.projectName=projectName;
        this.projectDescription=projectDescription;

    }


    /**
     * Retrieves the ID of the project.
     *
     * @return The ID of the project.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the project.
     *
     * @param id The ID of the project.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the project.
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param projectName The name of the project.
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Retrieves the description of the project.
     *
     * @return The description of the project.
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the description of the project.
     *
     * @param projectDescription The description of the project.
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Retrieves the deadline of the project.
     *
     * @return The deadline of the project.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline of the project.
     *
     * @param deadline The deadline of the project.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Retrieves the status of the project.
     *
     * @return The status of the project.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the project.
     *
     * @param status The status of the project.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the owner of the project.
     *
     * @return The owner of the project.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the project.
     *
     * @param owner The owner of the project.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Generates a string representation of the project.
     *
     * @return A string representation of the project.
     */
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", owner=" + (owner != null ? owner.getUsername() : "null") +
                '}';
    }
}

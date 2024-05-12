package com.example.TaskManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a task within a project management context.
 * Each task is associated with a specific project and can be assigned to a user.
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id", referencedColumnName = "id")
    private User assignedToUser;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    /**
     * Default constructor for JPA.
     */
    public Task() {
    }

    /**
     * Gets the task's unique identifier.
     *
     * @return An integer representing the task's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the task's unique identifier.
     *
     * @param id An integer representing the task's new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the task's name.
     *
     * @return A string representing the task's name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task's name.
     *
     * @param taskName A string containing the new name of the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the description of the task.
     *
     * @return A string representing the task's description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets the description of the task.
     *
     * @param taskDescription A string containing the new description of the task.
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Gets the deadline for completing the task.
     *
     * @return A Date object representing the deadline of the task.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline for the task.
     *
     * @param deadline A Date object representing the new deadline for the task.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the priority of the task.
     *
     * @return A string representing the task's priority level.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority A string representing the new priority level of the task.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Gets the current status of the task.
     *
     * @return A string representing the task's current status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the task.
     *
     * @param status A string representing the new status of the task.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the user to whom the task is assigned.
     *
     * @return A User object representing the user assigned to the task.
     */
    public User getAssignedToUser() {
        return assignedToUser;
    }

    /**
     * Sets the user to whom the task is assigned.
     *
     * @param assignedToUser A User object representing the new assignee of the task.
     */
    public void setAssignedToUser(User assignedToUser) {
        this.assignedToUser = assignedToUser;
    }

    /**
     * Gets the project associated with the task.
     *
     * @return A Project object representing the project to which the task belongs.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project associated with the task.
     *
     * @param project A Project object representing the new project association for the task.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Provides a string representation of the task, including all its properties.
     *
     * @return A string detailing the task's attributes.
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", deadline=" + deadline +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", assignedToUser=" + (assignedToUser != null ? assignedToUser.getUsername() : "null") +
                ", project=" + (project != null ? project.getProjectName() : "null") +
                '}';
    }
}

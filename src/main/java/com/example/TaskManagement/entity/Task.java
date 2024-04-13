package com.example.TaskManagement.entity;

import jakarta.persistence.*;

/**
 * Entity class representing a task in the system.
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

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedToUser;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * Default constructor for the Task class.
     */
    public Task() {
    }

    /**
     * Getter method for the task ID.
     *
     * @return The task ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for the task ID.
     *
     * @param id The task ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for the task name.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Setter method for the task name.
     *
     * @param taskName The task name to set.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Getter method for the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Setter method for the task description.
     *
     * @param taskDescription The task description to set.
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Getter method for the user to whom the task is assigned.
     *
     * @return The user to whom the task is assigned.
     */
    public User getAssignedToUser() {
        return assignedToUser;
    }
}
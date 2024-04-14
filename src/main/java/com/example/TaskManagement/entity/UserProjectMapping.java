package com.example.TaskManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name="userprojectmappings")
public class UserProjectMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id", nullable = false)
    private int userId;

    @Column(name="project_id", nullable = false)
    private int projectId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.example.TaskManagement.entity;

import com.example.TaskManagement.entity.Task;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(name="email", unique=true)
    private String email;

    @OneToMany(mappedBy = "assignedToUser", cascade = CascadeType.ALL)
    private List<Task> tasks;

    // Constructors and other methods omitted for brevity

    /**
     * Getter method for the list of tasks assigned to the user.
     * @return The list of tasks assigned to the user.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Setter method for the list of tasks assigned to the user.
     * @param tasks The list of tasks to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Other getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

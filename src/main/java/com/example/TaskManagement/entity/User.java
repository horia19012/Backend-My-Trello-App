package com.example.TaskManagement.entity;

import jakarta.persistence.*;

/**
 * Entity class representing a user in the system.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="full_name",nullable = false)
    private String fullName;

    @Column(name="email",unique=true)
    private String email;

    /**
     * Default constructor for the User class.
     */
    public User(){

    }

    /**
     * Parameterized constructor for the User class.
     * @param username The username of the user.
     * @param fullName The full name of the user.
     * @param email The email address of the user.
     */
    public User(String username,String fullName,String email){
        super();
        this.fullName=fullName;
        this.username=username;
        this.email=email;
    }

    /**
     * Getter method for the username.
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method for the full name.
     * @return The full name of the user.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter method for the full name.
     * @param fullName The full name to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter method for the email address.
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for the email address.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter method for the username.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the user ID.
     * @return The user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for the user ID.
     * @param id The user ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }
}
package com.example.TaskManagement.entity;

import jakarta.persistence.*;

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


    public User(){

    }
    public User(String username,String fullName,String email){
        super();
        this.fullName=fullName;
        this.username=username;
        this.email=email;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
package com.example.TaskManagement.DTO;

import com.example.TaskManagement.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDTO {

    private int id;


    private String username;

    private String fullName;


    private String email;


    private String password;

    public UserDTO(int id, String username, String fullName, String email, String password) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String username, String fullName, String email, String password) {
        this.username=username;
        this.fullName=fullName;
        this.email=email;
        this.password=password;
    }
    public UserDTO(){

    }
    public UserDTO(User user){
        this.username=user.getUsername();
        this.fullName=user.getFullName();
        this.email=user.getEmail();
        this.password=user.getPassword();
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}

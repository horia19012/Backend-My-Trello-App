package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {


}
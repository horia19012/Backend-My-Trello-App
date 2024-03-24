package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing user entities in the database.
 */
public interface UsersRepository extends JpaRepository<User, Integer> {

}

package com.example.TaskManagement.repository;

import com.example.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing user entities in the database.
 */
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<String> findUsernameById(int userId);
}

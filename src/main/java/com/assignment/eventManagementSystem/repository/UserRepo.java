package com.assignment.eventManagementSystem.repository;

import com.assignment.eventManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM `user` WHERE username = ?1 LIMIT 1", nativeQuery = true)
    Optional<User> findByUsername(String username);

}

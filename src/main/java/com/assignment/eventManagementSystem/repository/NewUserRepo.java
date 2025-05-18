package com.assignment.eventManagementSystem.repository;

import com.assignment.eventManagementSystem.entity.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NewUserRepo extends JpaRepository<NewUser, UUID> {
}

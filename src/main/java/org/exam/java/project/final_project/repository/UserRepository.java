package org.exam.java.project.final_project.repository;

import java.util.Optional;

import org.exam.java.project.final_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}

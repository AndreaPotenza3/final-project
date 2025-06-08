package org.exam.java.project.final_project.repository;

import java.util.List;

import org.exam.java.project.final_project.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
    List<Videogame> findByNameContainingIgnoreCase(String name);
}

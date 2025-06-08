package org.exam.java.project.final_project.repository;

import org.exam.java.project.final_project.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
  
}

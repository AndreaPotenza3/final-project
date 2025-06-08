package org.exam.java.project.final_project.service;

import java.util.List;
import java.util.Optional;

import org.exam.java.project.final_project.model.Platform;
import org.exam.java.project.final_project.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

     public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    public Platform getById(Integer id) {
        Optional<Platform> platformAttempt = platformRepository.findById(id);
        if(platformAttempt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return platformAttempt.get();
    }

    public Platform create(Platform platform) {
        return platformRepository.save(platform);
    }

    public Platform update(Platform platform) {
        return platformRepository.save(platform);
    }

    public void delete(Platform platform) {
        platformRepository.delete(platform);
    }

    public void deleteById(Integer id) {
        Platform platform = getById(id);
        platformRepository.delete(platform);
    }
    
}

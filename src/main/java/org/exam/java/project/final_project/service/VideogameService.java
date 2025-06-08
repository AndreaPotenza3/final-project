package org.exam.java.project.final_project.service;

import java.util.List;
import java.util.Optional;

import org.exam.java.project.final_project.model.Videogame;
import org.exam.java.project.final_project.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;

    public List<Videogame> findAll() {
        return videogameRepository.findAll();
    }

    public List<Videogame> findByInName(String name) {
        return videogameRepository.findByNameContainingIgnoreCase(name);
    }

    public Videogame getById(Integer id) {
        Optional<Videogame> videogameAttempt = videogameRepository.findById(id);
        if(videogameAttempt.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return videogameAttempt.get();
    }

    public Videogame create(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public Videogame update(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public void delete(Videogame videogame) {
        videogameRepository.delete(videogame);
    }

    public void deleteById(Integer id) {
        Videogame videogame = getById(id);
        videogameRepository.delete(videogame);
    }


    
}
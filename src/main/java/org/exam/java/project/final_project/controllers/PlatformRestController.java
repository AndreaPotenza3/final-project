package org.exam.java.project.final_project.controllers;

import java.util.List;
import java.util.Optional;

import org.exam.java.project.final_project.model.Platform;
import org.exam.java.project.final_project.model.Videogame;
import org.exam.java.project.final_project.service.PlatformService;
import org.exam.java.project.final_project.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/platforms")
public class PlatformRestController {
    
   
    @Autowired
    private PlatformService platformService;

    @GetMapping
    public List<Platform> index() {
        List<Platform> platforms = platformService.findAll();
        return platforms;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platform> show(@PathVariable Integer id) {
        Optional<Platform> platformAttempt = platformService.findById(id);
        if(platformAttempt.isEmpty()) {
            return new ResponseEntity<Platform>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Platform>(platformAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Platform> store(@Valid @RequestBody Platform platform) {
        return new ResponseEntity<Platform>(platformService.create(platform),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Platform> update(@Valid @RequestBody Platform platform, @PathVariable Integer id) {
        if(platformService.findById(id).isEmpty()) {
            return new ResponseEntity<Platform>(HttpStatus.NOT_FOUND);
        }
        platform.setId(id);
        return new ResponseEntity<Platform>(platformService.update(platform), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Platform> delete(@Valid @PathVariable Integer id) {
        if(platformService.findById(id).isEmpty()) {
            return new ResponseEntity<Platform>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Platform>(HttpStatus.OK);
    }        
}


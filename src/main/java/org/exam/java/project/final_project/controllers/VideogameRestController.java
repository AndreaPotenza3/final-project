package org.exam.java.project.final_project.controllers;

import java.util.List;
import java.util.Optional;

import org.exam.java.project.final_project.model.Videogame;
import org.exam.java.project.final_project.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/videogames")
public class VideogameRestController {
    
    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public List<Videogame> index() {
        List<Videogame> videogames = videogameService.findAll();
        return videogames;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> show(@PathVariable Integer id) {
        Optional<Videogame> videogameAttempt = videogameService.findById(id);
        if(videogameAttempt.isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Videogame>(videogameAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Videogame> store(@Valid @RequestBody Videogame videogame) {
        return new ResponseEntity<Videogame>(videogameService.create(videogame),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videogame> update(@Valid @RequestBody Videogame videogame, @PathVariable Integer id) {
        if(videogameService.findById(id).isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }
        videogame.setId(id);
        return new ResponseEntity<Videogame>(videogameService.update(videogame), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Videogame> delete(@Valid @PathVariable Integer id) {
        if(videogameService.findById(id).isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Videogame>(HttpStatus.OK);
    }        
}

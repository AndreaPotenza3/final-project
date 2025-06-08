package org.exam.java.project.final_project.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name cannot be null, blank or empty")
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "platforms")
    private List<Videogame> videogames;
    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Videogame> getVideogames() {
        return this.videogames;
    }

    public void setVideogames(List<Videogame> videogames) {
        this.videogames = videogames;
    }

}

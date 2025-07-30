package org.exam.java.project.final_project.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "videogames")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name cannot be null, blank or empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @NotBlank(message = "The name cannot be null, blank or empty")
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToMany
    @JsonIgnoreProperties("videogames")
    // @JsonManagedReference
    @JoinTable(name = "platform_videogame", joinColumns = @JoinColumn(name = "videogame_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private List<Platform> platforms;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Platform> getPlatforms() {
        return this.platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

}
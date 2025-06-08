package org.exam.java.project.final_project.controllers;

import java.util.List;

import org.exam.java.project.final_project.model.Videogame;
import org.exam.java.project.final_project.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameRepository repository;

    @GetMapping
    public String index(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Videogame> videogames;
        if(name != null && !name.isBlank()) {
            videogames = repository.findByNameContainingIgnoreCase(name);
        } else {
            videogames = repository.findAll();
        }

        model.addAttribute("videogames", videogames);
        model.addAttribute("name", name);
        return "videogames/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Videogame videogame = repository.findById(id).get();
        model.addAttribute("videogame",videogame);
        return "videogames/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("videogame", new Videogame());
        return "videogames/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "videogames/create";
        }
        repository.save(formVideogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("videogame", repository.findById(id).get());
        return "videogames/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult, Model model) {
         if(bindingResult.hasErrors()) {
            return "videogames/create";
        }
        repository.save(formVideogame);
        return "redirect:/videogames";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        
        return "redirect:/videogames";
    }
    
    
    
    
    
    
    

    
}
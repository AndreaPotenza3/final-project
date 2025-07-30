package org.exam.java.project.final_project.controllers;

import java.util.List;

import org.exam.java.project.final_project.model.Videogame;
import org.exam.java.project.final_project.service.PlatformService;
import org.exam.java.project.final_project.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private VideogameService videogameService;

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public String index(@RequestParam(name = "name", required = false) String name, Authentication authentication, Model model) {
        List<Videogame> videogames;
        if(name != null && !name.isBlank()) {
            videogames = videogameService.findByInName(name);
        } else {
            videogames = videogameService.findAll();
        }

        model.addAttribute("videogames", videogames);
        model.addAttribute("name", name);
        model.addAttribute("platforms", platformService.findAll());
        model.addAttribute("username", authentication.getName());
        return "videogames/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Videogame videogame = videogameService.getById(id);
        model.addAttribute("videogame",videogame);
        model.addAttribute("platforms", platformService.findAll());
        return "videogames/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("videogame", new Videogame());
        model.addAttribute("platforms", platformService.findAll());
        return "videogames/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "videogames/create-or-edit";
        }
        videogameService.create(formVideogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("videogame", videogameService.getById(id));
        model.addAttribute("edit", true);
        return "videogames/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult, Model model) {
         if(bindingResult.hasErrors()) {
            return "videogames/create-or-edit";
        }
        videogameService.update(formVideogame);
        return "redirect:/videogames";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        videogameService.deleteById(id);;
        
        return "redirect:/videogames";
    }
    
    
    
    
    
    
    

    
}
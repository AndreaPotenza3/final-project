package org.exam.java.project.final_project.controllers;

import java.util.List;

import org.exam.java.project.final_project.model.Platform;
import org.exam.java.project.final_project.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;


    @GetMapping
    public String index(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Platform> platforms = platformService.findAll();
        model.addAttribute("platforms", platforms);
        model.addAttribute("name", name);
        return "platforms/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Platform platform = platformService.getById(id);
        model.addAttribute("platform",platform);
        model.addAttribute("platforms", platformService.findAll());
        model.addAttribute("videogames", platform.getVideogames());
        return "platforms/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("platform", new Platform());
        return "platforms/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("platform") Platform formPlatform, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "platforms/create-or-edit";
        }
        platformService.create(formPlatform);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("platform", platformService.getById(id));
        model.addAttribute("edit", true);
        return "platforms/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("platform") Platform formPlatform, BindingResult bindingResult, Model model) {
         if(bindingResult.hasErrors()) {
            return "platforms/create-or-edit";
        }
        platformService.update(formPlatform);
        return "redirect:/platforms/{id}";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        platformService.delete(id);;
        
        return "redirect:/platforms";
    }
    
}
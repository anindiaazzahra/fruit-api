package com.example.fruit_api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.fruit_api.dto.FruitDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/fruits")
public class WebController {

    private final RestTemplate restTemplate;

    @GetMapping({"", "/"})
    public String listFruits(Model model) {
        String url = "http://localhost:8080/api/fruit/not-deleted";
        FruitDto[] fruitsArray = restTemplate.getForObject(url, FruitDto[].class);
        
        List<FruitDto> fruits = fruitsArray != null ? List.of(fruitsArray) : List.of();
        model.addAttribute("fruits", fruits);
        return "fruits/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("fruit", new FruitDto());
        return "fruits/create";
    }

    @PostMapping("/create")
    public String createFruit(@ModelAttribute FruitDto fruitDto) {
        String url = "http://localhost:8080/api/fruit";
        restTemplate.postForObject(url, fruitDto, FruitDto.class);
        return "redirect:/fruits";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        String url = "http://localhost:8080/api/fruit/" + id;
        FruitDto fruitDto = restTemplate.getForObject(url, FruitDto.class);
        model.addAttribute("fruit", fruitDto);
        return "fruits/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFruit(@PathVariable Long id, @ModelAttribute FruitDto fruitDto) {
        String url = "http://localhost:8080/api/fruit/" + id;
        restTemplate.put(url, fruitDto);
        return "redirect:/fruits";
    }

    @GetMapping("/delete/{id}")
    public String deleteFruit(@PathVariable Long id) {
        String url = "http://localhost:8080/api/fruit/" + id;
        restTemplate.delete(url);
        return "redirect:/fruits";
    }
}

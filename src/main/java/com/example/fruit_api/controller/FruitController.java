package com.example.fruit_api.controller;

import java.util.List;

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

import com.example.fruit_api.dto.FruitDto;
import com.example.fruit_api.service.FruitService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/fruit")
public class FruitController {

    private FruitService fruitService;

    @PostMapping
    public ResponseEntity<FruitDto> createFruit(@RequestBody FruitDto fruitDto){
        FruitDto savedFruit = fruitService.createFruit(fruitDto);

        return new ResponseEntity<>(savedFruit, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<FruitDto> getFruitById(@PathVariable("id") Long fruitId){
        FruitDto fruitDto = fruitService.getFruitById(fruitId);
        
        return ResponseEntity.ok(fruitDto);
    }

    @GetMapping
    public ResponseEntity<List<FruitDto>> getAllFruits(){
        List<FruitDto> fruits = fruitService.getAllFruits();

        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/not-deleted")
    public ResponseEntity<List<FruitDto>> getAllNotDeletedFruits() {
        List<FruitDto> fruits = fruitService.getAllNotDeletedFruits();
        return ResponseEntity.ok(fruits);
    }

    @PutMapping("{id}")
    public ResponseEntity<FruitDto> updateFruit(@PathVariable("id") Long fruitId, 
                                                @RequestBody FruitDto updatedFruit){
        FruitDto fruitDto = fruitService.updateFruit(fruitId, updatedFruit);

        return ResponseEntity.ok(fruitDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable("id") Long fruitId){
        fruitService.softDeleteFruit(fruitId);
        return ResponseEntity.ok("Fruit deleted successfully!");
    }
}

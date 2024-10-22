package com.example.fruit_api.service;

import java.util.List;

import com.example.fruit_api.dto.FruitDto;

public interface FruitService {
    FruitDto createFruit(FruitDto fruitDto);

    FruitDto getFruitById(Long fruitId);

    List<FruitDto> getAllFruits();

    List<FruitDto> getAllNotDeletedFruits();

    FruitDto updateFruit(Long fruitId, FruitDto updatedFruit);

    void softDeleteFruit(Long fruitId);
}

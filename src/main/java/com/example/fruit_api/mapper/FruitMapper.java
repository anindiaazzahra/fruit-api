package com.example.fruit_api.mapper;

import com.example.fruit_api.dto.FruitDto;
import com.example.fruit_api.entity.Fruit;

public class FruitMapper {

    public static FruitDto mapToFruitDto(Fruit fruit){
        return new FruitDto(
            fruit.getId(),
            fruit.getName(),
            fruit.getIsDeleted(),
            fruit.getCreatedAt(),
            fruit.getUpdatedAt(),
            fruit.getDeletedAt(),
            fruit.getPrice(),
            fruit.getDescription()
        );
    }

    public static Fruit mapToFruit(FruitDto fruitDto){
        return new Fruit(
            fruitDto.getId(),
            fruitDto.getName(),
            fruitDto.getIsDeleted(),
            fruitDto.getCreatedAt(),
            fruitDto.getUpdatedAt(),
            fruitDto.getDeletedAt(),
            fruitDto.getPrice(),
            fruitDto.getDescription()
        );
    }
}

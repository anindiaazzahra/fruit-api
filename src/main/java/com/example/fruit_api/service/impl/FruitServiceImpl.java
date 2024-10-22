package com.example.fruit_api.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.fruit_api.dto.FruitDto;
import com.example.fruit_api.entity.Fruit;
import com.example.fruit_api.exception.DataNotFoundException;
import com.example.fruit_api.mapper.FruitMapper;
import com.example.fruit_api.repository.FruitRepository;
import com.example.fruit_api.service.FruitService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FruitServiceImpl implements FruitService {

    private FruitRepository fruitRepository;

    @Override
    public FruitDto createFruit(FruitDto fruitDto) {
        
        Fruit fruit = FruitMapper.mapToFruit(fruitDto);
        fruit.setIsDeleted(false);
        Fruit savedFruit = fruitRepository.save(fruit);

        return FruitMapper.mapToFruitDto(savedFruit);
    }

    @Override
    public FruitDto getFruitById(Long fruitId) {
        Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(
            () -> new DataNotFoundException("Fruit with id " + fruitId + " doesn't exist"));
        
        return FruitMapper.mapToFruitDto(fruit);
    }

    @Override
    public List<FruitDto> getAllFruits() {
        List<Fruit> fruits = fruitRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        
        return fruits.stream().map((fruit) -> FruitMapper.mapToFruitDto(fruit))
            .collect(Collectors.toList());
    }

    @Override
    public List<FruitDto> getAllNotDeletedFruits() {
        List<Fruit> fruits = fruitRepository.findAllNotDeleted(Sort.by(Sort.Direction.DESC, "id"));
        
        return fruits.stream().map((fruit) -> FruitMapper.mapToFruitDto(fruit))
            .collect(Collectors.toList());
    }

    @Override
    public FruitDto updateFruit(Long fruitId, FruitDto updatedFruit) {
        Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(
            () -> new DataNotFoundException("Fruit with id " + fruitId + " doesn't exist")
        );

        fruit.setName(updatedFruit.getName());
        fruit.setDescription(updatedFruit.getDescription());
        fruit.setPrice(updatedFruit.getPrice());
        updatedFruit.setCreatedAt(fruit.getCreatedAt());
        
        Fruit updatedFruitObj = fruitRepository.save(fruit);

        return FruitMapper.mapToFruitDto(updatedFruitObj);   
    }

    @Override
    public void softDeleteFruit(Long fruitId) {
        Fruit fruit = fruitRepository.findById(fruitId).orElseThrow(
            () -> new DataNotFoundException("Fruit with id " + fruitId + " doesn't exist")
        );

        fruit.setIsDeleted(true);
        fruit.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        fruitRepository.save(fruit);
    }
}

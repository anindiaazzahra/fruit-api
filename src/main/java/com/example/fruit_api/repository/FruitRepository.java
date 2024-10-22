package com.example.fruit_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;

import com.example.fruit_api.entity.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    @Query("SELECT f FROM Fruit f WHERE f.isDeleted = false")
    List<Fruit> findAllNotDeleted(Sort sort);
}

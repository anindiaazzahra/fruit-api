package com.example.fruit_api.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FruitDto {
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;

    private Boolean isDeleted;

    @NotNull(message = "CreatedAt is required")
    private Timestamp createdAt;

    @NotNull(message = "UpdatedAt is required")
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    @NotNull(message = "Price is required")
    private Integer price;

    private String description;
}

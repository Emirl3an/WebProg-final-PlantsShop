package com.example.project.web.dto;

import lombok.Data;

@Data
public class PlantDTO {
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private String description;
    private String imageName;
}

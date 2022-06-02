package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    private double price;
    private String description;
    private String imageName;
}

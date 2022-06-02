package com.example.project.repository;

import com.example.project.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findAllByCategory_Id(int id);
}

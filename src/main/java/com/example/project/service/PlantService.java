package com.example.project.service;

import com.example.project.model.Plant;
import com.example.project.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public List<Plant> getAllPlant() {
        return plantRepository.findAll();
    }

    public void addPlant(Plant plant) {
        plantRepository.save(plant);
    }

    public void removePlantById(Long id) {
        plantRepository.deleteById(id);
    }

    public Optional<Plant> getPlantById(Long id) {
        return plantRepository.findById(id);
    }

    public List<Plant> getAllPlantsByCategoryId(int id) {
        return plantRepository.findAllByCategory_Id(id);
    }
}

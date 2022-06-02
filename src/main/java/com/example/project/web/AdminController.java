package com.example.project.web;

import com.example.project.model.Category;
import com.example.project.model.Plant;
import com.example.project.service.CategoryService;
import com.example.project.service.PlantService;
import com.example.project.web.dto.PlantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

//@EnableSwagger2
//@RestController
@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/plantImages";
    @Autowired
    CategoryService categoryService;

    @Autowired
    PlantService plantService;

    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);

        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else
            return "404";
    }

    //plant section
    @GetMapping("/admin/plants")
    public String plants(Model model) {
        model.addAttribute("plants", plantService.getAllPlant());
        return "plants";
    }

    @GetMapping("/admin/plants/add")
    public String plantAddGet(Model model) {
        model.addAttribute("plantDTO", new PlantDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "plantsAdd";
    }

    @PostMapping("/admin/plants/add")
    public String plantAddPost(@ModelAttribute("plant") PlantDTO plantDTO,
                                 @RequestParam("plantImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Plant plant = new Plant();
        plant.setId(plantDTO.getId());
        plant.setName(plantDTO.getName());
        plant.setCategory(categoryService.getCategoryById(plantDTO.getCategoryId()).get());
        plant.setPrice(plantDTO.getPrice());
        plant.setDescription(plantDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        plant.setImageName(imageUUID);
        plantService.addPlant(plant);
        return "redirect:/admin/plants";
    }

    @GetMapping("/admin/plant/delete/{id}")
    public String deletePlant(@PathVariable Long id) {
        plantService.removePlantById(id);
        return "redirect:/admin/plants";
    }

    @GetMapping("admin/plant/update/{id}")
    public String updatePlantGet(@PathVariable Long id, Model model) {
        Plant plant = plantService.getPlantById(id).get();
        PlantDTO plantDTO = new PlantDTO();
        plantDTO.setId(plant.getId());
        plantDTO.setName(plant.getName());
        plantDTO.setCategoryId(plant.getCategory().getId());
        plantDTO.setPrice(plant.getPrice());
        plantDTO.setDescription(plant.getDescription());
        plantDTO.setImageName(plant.getImageName());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("plantDTO", plantDTO);
        return "plantsAdd";
    }
}
